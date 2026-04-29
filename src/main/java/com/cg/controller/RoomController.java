package com.cg.controller;

import com.cg.entity.Room;
import com.cg.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/{typeId}")
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room,
                                           @PathVariable Long typeId) {
        return ResponseEntity.ok(service.createRoom(room, typeId));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(service.getAllRooms());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> available() {
        return ResponseEntity.ok(service.getAvailableRooms());
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Room>> unavailable() {
        return ResponseEntity.ok(service.getUnavailableRooms());
    }

    @GetMapping("/type/{typeName}")
    public ResponseEntity<List<Room>> byType(@PathVariable String typeName) {
        return ResponseEntity.ok(service.getRoomsByType(typeName));
    }
    @GetMapping("/price")
    public ResponseEntity<List<Room>> byPrice(@RequestParam double min,
                                             @RequestParam double max) {
        return ResponseEntity.ok(service.getRoomsByPrice(min, max));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PutMapping("/{id}/availability")
    public ResponseEntity<Room> updateAvailability(@PathVariable Long id,
                                                   @RequestParam boolean status) {
        return ResponseEntity.ok(service.updateAvailability(id, status));
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<Room> byNumber(@PathVariable int roomNumber) {
        return ResponseEntity.ok(service.getByRoomNumber(roomNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}