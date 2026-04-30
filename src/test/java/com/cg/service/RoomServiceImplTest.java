package com.cg.service;

import com.cg.entity.Room;
import com.cg.entity.RoomType;
import com.cg.exception.BadRequestException;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomRepository;
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
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepo;

    @Mock
    private RoomTypeRepository typeRepo;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private RoomType roomType;

    @BeforeEach
    void setUp() {
        roomType = new RoomType();
        roomType.setRoomTypeId(1L);
        roomType.setTypeName("Deluxe");
        roomType.setPricePerNight(3000.0);

        room = new Room();
        room.setRoomId(1L);
        room.setRoomNumber(101);
        room.setIsAvailable(true);
        room.setRoomType(roomType);
    }

    @Test
    void testCreateRoom() {
        when(roomRepo.existsById(1L)).thenReturn(false);
        when(typeRepo.findById(1L)).thenReturn(Optional.of(roomType));
        when(roomRepo.save(room)).thenReturn(room);

        Room result = roomService.createRoom(room, 1L);

        assertEquals(101, result.getRoomNumber());
        assertEquals(roomType, result.getRoomType());
        verify(roomRepo).save(room);
    }

    @Test
    void testCreateRoomInvalidRoomNumber() {
        room.setRoomNumber(0);

        assertThrows(BadRequestException.class,
                () -> roomService.createRoom(room, 1L));
    }

    @Test
    void testCreateRoomAlreadyExists() {
        when(roomRepo.existsById(1L)).thenReturn(true);

        assertThrows(ConflictException.class,
                () -> roomService.createRoom(room, 1L));
    }

    @Test
    void testCreateRoomTypeNotFound() {
        when(roomRepo.existsById(1L)).thenReturn(false);
        when(typeRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roomService.createRoom(room, 1L));
    }

    @Test
    void testGetAllRooms() {
        when(roomRepo.findAll()).thenReturn(List.of(room));

        List<Room> result = roomService.getAllRooms();

        assertEquals(1, result.size());
        verify(roomRepo).findAll();
    }

    @Test
    void testGetById() {
        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomService.getById(1L);

        assertEquals(1L, result.getRoomId());
    }

    @Test
    void testGetByIdNotFound() {
        when(roomRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roomService.getById(1L));
    }

    @Test
    void testGetAvailableRooms() {
        when(roomRepo.findByIsAvailableTrue()).thenReturn(List.of(room));

        List<Room> result = roomService.getAvailableRooms();

        assertEquals(1, result.size());
    }

    @Test
    void testGetUnavailableRooms() {
        when(roomRepo.findByIsAvailableFalse()).thenReturn(List.of(room));

        List<Room> result = roomService.getUnavailableRooms();

        assertEquals(1, result.size());
    }

    @Test
    void testGetRoomsByType() {
        when(roomRepo.findByRoomType_TypeName("Deluxe")).thenReturn(List.of(room));

        List<Room> result = roomService.getRoomsByType("Deluxe");

        assertEquals(1, result.size());
    }

    @Test
    void testGetRoomsByPrice() {
        when(roomRepo.findByRoomType_PricePerNightBetween(1000.0, 5000.0))
                .thenReturn(List.of(room));

        List<Room> result = roomService.getRoomsByPrice(1000.0, 5000.0);

        assertEquals(1, result.size());
    }

    @Test
    void testGetRoomsByPriceInvalidRange() {
        assertThrows(BadRequestException.class,
                () -> roomService.getRoomsByPrice(5000.0, 1000.0));
    }

    @Test
    void testGetByRoomNumber() {
        when(roomRepo.findByRoomNumber(101)).thenReturn(Optional.of(room));

        Room result = roomService.getByRoomNumber(101);

        assertEquals(101, result.getRoomNumber());
    }

    @Test
    void testGetByRoomNumberNotFound() {
        when(roomRepo.findByRoomNumber(101)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> roomService.getByRoomNumber(101));
    }

    @Test
    void testUpdateAvailability() {
        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepo.save(room)).thenReturn(room);

        Room result = roomService.updateAvailability(1L, false);

        assertFalse(result.getIsAvailable());
        verify(roomRepo).save(room);
    }

    @Test
    void testDeleteRoom() {
        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));

        roomService.deleteRoom(1L);

        verify(roomRepo).delete(room);
    }
}
