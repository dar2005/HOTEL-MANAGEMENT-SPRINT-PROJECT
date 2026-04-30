package com.cg.controller;

import com.cg.entity.RoomType;
import com.cg.service.RoomTypeService;

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
class RoomTypeControllerTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private RoomTypeController roomTypeController;

    @Test
    void testGetAllTypes() {
        List<RoomType> types = List.of(new RoomType(), new RoomType());
        when(roomTypeService.getAllTypes()).thenReturn(types);

        ResponseEntity<List<RoomType>> result = roomTypeController.getAll();

        assertEquals(2, result.getBody().size());
        verify(roomTypeService).getAllTypes();
    }

    @Test
    void testGetById() {
        RoomType roomType = new RoomType();
        when(roomTypeService.getById(1L)).thenReturn(roomType);

        ResponseEntity<RoomType> result = roomTypeController.getById(1L);

        assertEquals(roomType, result.getBody());
        verify(roomTypeService).getById(1L);
    }

    @Test
    void testGetByName() {
        RoomType roomType = new RoomType();
        when(roomTypeService.getByName("Deluxe")).thenReturn(roomType);

        ResponseEntity<RoomType> result = roomTypeController.getByName("Deluxe");

        assertEquals(roomType, result.getBody());
        verify(roomTypeService).getByName("Deluxe");
    }

    @Test
    void testCreateRoomType() {
        RoomType roomType = new RoomType();
        when(roomTypeService.create(roomType)).thenReturn(roomType);

        ResponseEntity<RoomType> result = roomTypeController.create(roomType);

        assertEquals(roomType, result.getBody());
        verify(roomTypeService).create(roomType);
    }
}
