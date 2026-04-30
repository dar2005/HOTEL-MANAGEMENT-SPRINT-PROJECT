package com.cg.service;

import com.cg.entity.RoomType;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomTypeRepository;

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
class RoomTypeServiceImplTest {

    @Mock
    private RoomTypeRepository repo;

    @InjectMocks
    private RoomTypeServiceImpl roomTypeService;

    private RoomType roomType;

    @BeforeEach
    void setUp() {
        roomType = new RoomType();
        roomType.setRoomTypeId(1L);
        roomType.setTypeName("Deluxe");
        roomType.setDescription("Large room");
        roomType.setMaxOccupancy(3);
        roomType.setPricePerNight(3000.0);
    }

    @Test
    void testGetAllTypes() {
        when(repo.findAll()).thenReturn(List.of(roomType));

        List<RoomType> result = roomTypeService.getAllTypes();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }

    @Test
    void testGetById() {
        when(repo.findById(1L)).thenReturn(Optional.of(roomType));

        RoomType result = roomTypeService.getById(1L);

        assertEquals(1L, result.getRoomTypeId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roomTypeService.getById(1L));
    }

    @Test
    void testGetByName() {
        when(repo.findByTypeName("Deluxe")).thenReturn(Optional.of(roomType));

        RoomType result = roomTypeService.getByName("Deluxe");

        assertEquals("Deluxe", result.getTypeName());
    }

    @Test
    void testGetByNameNotFound() {
        when(repo.findByTypeName("Deluxe")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roomTypeService.getByName("Deluxe"));
    }

    @Test
    void testCreateRoomType() {
        when(repo.findByTypeName("Deluxe")).thenReturn(Optional.empty());
        when(repo.save(roomType)).thenReturn(roomType);

        RoomType result = roomTypeService.create(roomType);

        assertEquals("Deluxe", result.getTypeName());
        verify(repo).save(roomType);
    }

    @Test
    void testCreateRoomTypeAlreadyExists() {
        when(repo.findByTypeName("Deluxe")).thenReturn(Optional.of(roomType));

        assertThrows(ConflictException.class,
                () -> roomTypeService.create(roomType));
        verify(repo, never()).save(roomType);
    }
}
