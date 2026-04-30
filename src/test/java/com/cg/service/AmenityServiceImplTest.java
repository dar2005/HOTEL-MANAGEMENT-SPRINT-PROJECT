package com.cg.service;

import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;
import com.cg.entity.Amenity;
import com.cg.entity.Room;
import com.cg.repo.AmenityRepository;
import com.cg.repo.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AmenityServiceImplTest {

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private AmenityServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // 🔹 1. CREATE AMENITY
    @Test
    void testCreateAmenity() {
        AmenityRequestDTO dto = new AmenityRequestDTO();
        dto.setAmenityId(1L);
        dto.setName("WiFi");
        dto.setDescription("Free Internet");

        Amenity saved = new Amenity();
        saved.setAmenityId(1L);
        saved.setName("WiFi");
        saved.setDescription("Free Internet");

        when(amenityRepository.save(any())).thenReturn(saved);

        AmenityResponseDTO result = service.createAmenity(dto);

        assertNotNull(result);
        assertEquals("WiFi", result.getName());
        verify(amenityRepository).save(any());
    }

    // 🔹 2. GET ALL
    @Test
    void testGetAllAmenities() {
        Amenity a = new Amenity();
        a.setAmenityId(1L);
        a.setName("AC");

        when(amenityRepository.findAll()).thenReturn(List.of(a));

        List<AmenityResponseDTO> result = service.getAllAmenities();

        assertEquals(1, result.size());
    }

    // 🔹 3. GET BY ID SUCCESS
    @Test
    void testGetAmenityById_success() {
        Amenity a = new Amenity();
        a.setAmenityId(1L);
        a.setName("TV");

        when(amenityRepository.findById(1L)).thenReturn(Optional.of(a));

        AmenityResponseDTO result = service.getAmenityById(1L);

        assertEquals("TV", result.getName());
    }

    // 🔹 4. GET BY ID FAIL
    @Test
    void testGetAmenityById_notFound() {
        when(amenityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.getAmenityById(1L));
    }

    // 🔹 5. UPDATE
    @Test
    void testUpdateAmenity() {
        Amenity existing = new Amenity();
        existing.setAmenityId(1L);

        AmenityRequestDTO dto = new AmenityRequestDTO();
        dto.setName("Updated");
        dto.setDescription("Updated desc");

        when(amenityRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(amenityRepository.save(any())).thenReturn(existing);

        AmenityResponseDTO result = service.updateAmenity(1L, dto);

        assertEquals("Updated", result.getName());
    }

    // 🔹 6. DELETE
    @Test
    void testDeleteAmenity() {
        service.deleteAmenity(1L);
        verify(amenityRepository, times(1)).deleteById(1L);
    }

    // 🔹 7. GET BY NAME SUCCESS
    @Test
    void testGetAmenityByName_success() {
        Amenity a = new Amenity();
        a.setName("Pool");

        when(amenityRepository.findByName("Pool")).thenReturn(a);

        AmenityResponseDTO result = service.getAmenityByName("Pool");

        assertEquals("Pool", result.getName());
    }

    // 🔹 8. GET BY NAME FAIL
    @Test
    void testGetAmenityByName_notFound() {
        when(amenityRepository.findByName("Gym")).thenReturn(null);

        assertThrows(RuntimeException.class,
                () -> service.getAmenityByName("Gym"));
    }

    // 🔹 9. EXISTS
    @Test
    void testExistsById() {
        when(amenityRepository.existsById(1L)).thenReturn(true);

        boolean exists = service.existsById(1L);

        assertTrue(exists);
    }

    // 🔹 10. COUNT
    @Test
    void testCountAmenities() {
        when(amenityRepository.count()).thenReturn(5L);

        long count = service.countAmenities();

        assertEquals(5, count);
    }

    // 🔹 11. ASSIGN AMENITY TO ROOM (FIXED: ArrayList)
    @Test
    void testAssignAmenityToRoom() {
        Room room = new Room();
        room.setAmenities(new ArrayList<>());  // ✅ FIXED

        Amenity amenity = new Amenity();
        amenity.setAmenityId(1L);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(amenityRepository.findById(1L)).thenReturn(Optional.of(amenity));

        service.assignAmenityToRoom(1L, 1L);

        assertTrue(room.getAmenities().contains(amenity));
        verify(roomRepository).save(room);
    }

    // 🔹 12. REMOVE AMENITY FROM ROOM (FIXED: ArrayList)
    @Test
    void testRemoveAmenityFromRoom() {
        Amenity amenity = new Amenity();
        amenity.setAmenityId(1L);

        Room room = new Room();
        room.setAmenities(new ArrayList<>(List.of(amenity)));  // ✅ FIXED

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(amenityRepository.findById(1L)).thenReturn(Optional.of(amenity));

        service.removeAmenityFromRoom(1L, 1L);

        assertFalse(room.getAmenities().contains(amenity));
        verify(roomRepository).save(room);
    }
}