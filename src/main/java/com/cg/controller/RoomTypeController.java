package com.cg.controller;

import com.cg.entity.RoomType;
import com.cg.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeService service;

    // 1️⃣ Get all room types
    @GetMapping
    public List<RoomType> getAll() {
        return service.getAllTypes();
    }

    // 2️⃣ Get by ID
    @GetMapping("/{id}")
    public RoomType getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 3️⃣ Get by name
    @GetMapping("/name/{name}")
    public RoomType getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    // 4️⃣ Create room type
    @PostMapping
    public RoomType create(@RequestBody RoomType roomType) {
        return service.create(roomType);
    }
}