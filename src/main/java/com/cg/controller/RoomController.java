package com.cg.controller;

import com.cg.entity.Room;
import com.cg.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/{typeId}")
    public Room createRoom(@RequestBody Room room,
                           @PathVariable Long typeId) {
        return service.createRoom(room, typeId);
    }

    @GetMapping
    public List<Room> getAll() {
        return service.getAllRooms();
    }

    @GetMapping("/available")
    public List<Room> available() {
        return service.getAvailableRooms();
    }

    @GetMapping("/unavailable")
    public List<Room> unavailable() {
        return service.getUnavailableRooms();
    }

    @GetMapping("/type/{typeName}")
    public List<Room> byType(@PathVariable String typeName) {
        return service.getRoomsByType(typeName);
    }

    @GetMapping("/price")
    public List<Room> byPrice(@RequestParam double min,
                             @RequestParam double max) {
        return service.getRoomsByPrice(min, max);
    }

    @GetMapping("/number/{roomNumber}")
    public List<Room> byNumber(@PathVariable int roomNumber) {
        return service.getByRoomNumber(roomNumber);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteRoom(id);
        return "Room deleted successfully";
    }
}