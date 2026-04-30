package com.cg.service;

import com.cg.dto.HotelRequestDTO;
import com.cg.dto.HotelResponseDTO;
import com.cg.entity.Hotel;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.HotelRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel hotel;
    private HotelRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setHotelId(1L);
        hotel.setName("Taj Palace");
        hotel.setLocation("Mumbai");
        hotel.setDescription("Luxury hotel near the sea");

        requestDTO = new HotelRequestDTO(
                "Taj Palace",
                "Mumbai",
                "Luxury hotel near the sea"
        );
    }

    @Test
    void createHotel_success() {
        when(hotelRepository.existsById(1L)).thenReturn(false);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        HotelResponseDTO response = hotelService.createHotel(1L, requestDTO);

        assertEquals(1L, response.getHotelId());
        assertEquals("Taj Palace", response.getName());
        assertEquals("Mumbai", response.getLocation());
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    void createHotel_alreadyExists() {
        when(hotelRepository.existsById(1L)).thenReturn(true);

        assertThrows(ConflictException.class,
                () -> hotelService.createHotel(1L, requestDTO));
        verify(hotelRepository, never()).save(any(Hotel.class));
    }

    @Test
    void getAllHotels_success() {
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        List<HotelResponseDTO> hotels = hotelService.getAllHotels();

        assertEquals(1, hotels.size());
        assertEquals("Taj Palace", hotels.get(0).getName());
    }

    @Test
    void getHotelById_success() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        HotelResponseDTO response = hotelService.getHotelById(1L);

        assertEquals(1L, response.getHotelId());
        assertEquals("Mumbai", response.getLocation());
    }

    @Test
    void getHotelById_notFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> hotelService.getHotelById(1L));
    }

    @Test
    void searchByLocation_success() {
        when(hotelRepository.findByLocationContainingIgnoreCase("mum"))
                .thenReturn(List.of(hotel));

        List<HotelResponseDTO> hotels = hotelService.searchByLocation("mum");

        assertEquals(1, hotels.size());
        assertEquals("Mumbai", hotels.get(0).getLocation());
    }

    @Test
    void searchByName_success() {
        when(hotelRepository.findByNameContainingIgnoreCase("taj"))
                .thenReturn(List.of(hotel));

        List<HotelResponseDTO> hotels = hotelService.searchByName("taj");

        assertEquals(1, hotels.size());
        assertEquals("Taj Palace", hotels.get(0).getName());
    }

    @Test
    void searchByLocationAndName_success() {
        when(hotelRepository.findByLocationContainingIgnoreCaseAndNameContainingIgnoreCase("mum", "taj"))
                .thenReturn(List.of(hotel));

        List<HotelResponseDTO> hotels = hotelService.searchByLocationAndName("mum", "taj");

        assertEquals(1, hotels.size());
        assertEquals("Taj Palace", hotels.get(0).getName());
    }

    @Test
    void updateHotel_success() {
        HotelRequestDTO updateDTO = new HotelRequestDTO(
                "Updated Palace",
                "Delhi",
                "Updated description"
        );

        Hotel updatedHotel = new Hotel();
        updatedHotel.setHotelId(1L);
        updatedHotel.setName("Updated Palace");
        updatedHotel.setLocation("Delhi");
        updatedHotel.setDescription("Updated description");

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(updatedHotel);

        HotelResponseDTO response = hotelService.updateHotel(1L, updateDTO);

        assertEquals("Updated Palace", response.getName());
        assertEquals("Delhi", response.getLocation());
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    void updateHotel_notFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> hotelService.updateHotel(1L, requestDTO));
        verify(hotelRepository, never()).save(any(Hotel.class));
    }
}