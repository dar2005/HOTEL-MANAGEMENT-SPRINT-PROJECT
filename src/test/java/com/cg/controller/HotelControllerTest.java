package com.cg.controller;

import com.cg.dto.HotelRequestDTO;
import com.cg.dto.HotelResponseDTO;
import com.cg.service.HotelService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    void testCreateHotel() {
        HotelRequestDTO request = new HotelRequestDTO("Taj Palace", "Mumbai", "Luxury hotel");
        HotelResponseDTO response = new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel");

        when(hotelService.createHotel(1L, request)).thenReturn(response);

        ResponseEntity<HotelResponseDTO> result = hotelController.createHotel(1L, request);

        assertEquals(response, result.getBody());
        verify(hotelService).createHotel(1L, request);
    }

    @Test
    void testGetAllHotels() {
        List<HotelResponseDTO> hotels = List.of(
                new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel"),
                new HotelResponseDTO(2L, "Sea View", "Goa", "Beach hotel")
        );

        when(hotelService.getAllHotels()).thenReturn(hotels);

        ResponseEntity<List<HotelResponseDTO>> result = hotelController.getAllHotels();

        assertEquals(2, result.getBody().size());
        verify(hotelService).getAllHotels();
    }

    @Test
    void testGetHotelById() {
        HotelResponseDTO response = new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel");

        when(hotelService.getHotelById(1L)).thenReturn(response);

        ResponseEntity<HotelResponseDTO> result = hotelController.getHotelById(1L);

        assertEquals(response, result.getBody());
        verify(hotelService).getHotelById(1L);
    }

    @Test
    void testSearchByLocation() {
        List<HotelResponseDTO> hotels = List.of(
                new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel")
        );

        when(hotelService.searchByLocation("Mumbai")).thenReturn(hotels);

        ResponseEntity<List<HotelResponseDTO>> result = hotelController.searchByLocation("Mumbai");

        assertEquals(1, result.getBody().size());
        verify(hotelService).searchByLocation("Mumbai");
    }

    @Test
    void testSearchByName() {
        List<HotelResponseDTO> hotels = List.of(
                new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel")
        );

        when(hotelService.searchByName("Taj")).thenReturn(hotels);

        ResponseEntity<List<HotelResponseDTO>> result = hotelController.searchByName("Taj");

        assertEquals(1, result.getBody().size());
        verify(hotelService).searchByName("Taj");
    }

    @Test
    void testSearchByLocationAndName() {
        List<HotelResponseDTO> hotels = List.of(
                new HotelResponseDTO(1L, "Taj Palace", "Mumbai", "Luxury hotel")
        );

        when(hotelService.searchByLocationAndName("Mumbai", "Taj")).thenReturn(hotels);

        ResponseEntity<List<HotelResponseDTO>> result =
                hotelController.searchByLocationAndName("Mumbai", "Taj");

        assertEquals(1, result.getBody().size());
        verify(hotelService).searchByLocationAndName("Mumbai", "Taj");
    }

    @Test
    void testUpdateHotel() {
        HotelRequestDTO request = new HotelRequestDTO("Updated Palace", "Delhi", "Updated hotel");
        HotelResponseDTO response = new HotelResponseDTO(1L, "Updated Palace", "Delhi", "Updated hotel");

        when(hotelService.updateHotel(1L, request)).thenReturn(response);

        ResponseEntity<HotelResponseDTO> result = hotelController.updateHotel(1L, request);

        assertEquals(response, result.getBody());
        verify(hotelService).updateHotel(1L, request);
    }
}