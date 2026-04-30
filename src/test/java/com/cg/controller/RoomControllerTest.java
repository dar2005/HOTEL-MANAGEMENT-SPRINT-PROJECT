package com.cg.controller;

import com.cg.entity.Room;
import com.cg.service.RoomService;

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
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    void testCreateRoom() {
        Room room = new Room();
        when(roomService.createRoom(room, 1L)).thenReturn(room);

        ResponseEntity<Room> result = roomController.createRoom(room, 1L);

        assertEquals(room, result.getBody());
        verify(roomService).createRoom(room, 1L);
    }

    @Test
    void testGetAllRooms() {
        List<Room> rooms = List.of(new Room(), new Room());
        when(roomService.getAllRooms()).thenReturn(rooms);

        ResponseEntity<List<Room>> result = roomController.getAll();

        assertEquals(2, result.getBody().size());
        verify(roomService).getAllRooms();
    }

    @Test
    void testGetAvailableRooms() {
        List<Room> rooms = List.of(new Room());
        when(roomService.getAvailableRooms()).thenReturn(rooms);

        ResponseEntity<List<Room>> result = roomController.available();

        assertEquals(1, result.getBody().size());
        verify(roomService).getAvailableRooms();
    }

    @Test
    void testGetUnavailableRooms() {
        List<Room> rooms = List.of(new Room());
        when(roomService.getUnavailableRooms()).thenReturn(rooms);

        ResponseEntity<List<Room>> result = roomController.unavailable();

        assertEquals(1, result.getBody().size());
        verify(roomService).getUnavailableRooms();
    }

    @Test
    void testGetRoomsByType() {
        List<Room> rooms = List.of(new Room());
        when(roomService.getRoomsByType("Deluxe")).thenReturn(rooms);

        ResponseEntity<List<Room>> result = roomController.byType("Deluxe");

        assertEquals(1, result.getBody().size());
        verify(roomService).getRoomsByType("Deluxe");
    }

    @Test
    void testGetRoomsByPrice() {
        List<Room> rooms = List.of(new Room());
        when(roomService.getRoomsByPrice(1000.0, 5000.0)).thenReturn(rooms);

        ResponseEntity<List<Room>> result = roomController.byPrice(1000.0, 5000.0);

        assertEquals(1, result.getBody().size());
        verify(roomService).getRoomsByPrice(1000.0, 5000.0);
    }

    @Test
    void testGetRoomById() {
        Room room = new Room();
        when(roomService.getById(1L)).thenReturn(room);

        ResponseEntity<Room> result = roomController.getById(1L);

        assertEquals(room, result.getBody());
        verify(roomService).getById(1L);
    }

    @Test
    void testUpdateAvailability() {
        Room room = new Room();
        when(roomService.updateAvailability(1L, true)).thenReturn(room);

        ResponseEntity<Room> result = roomController.updateAvailability(1L, true);

        assertEquals(room, result.getBody());
        verify(roomService).updateAvailability(1L, true);
    }

    @Test
    void testGetRoomByNumber() {
        Room room = new Room();
        when(roomService.getByRoomNumber(101)).thenReturn(room);

        ResponseEntity<Room> result = roomController.byNumber(101);

        assertEquals(room, result.getBody());
        verify(roomService).getByRoomNumber(101);
    }

    @Test
    void testDeleteRoom() {
        ResponseEntity<String> result = roomController.delete(1L);

        assertEquals("Room deleted successfully", result.getBody());
        verify(roomService).deleteRoom(1L);
    }
}
