package com.cg.controller;

import com.cg.entity.RoomType;
import com.cg.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtypes")
public class RoomTypeController {

    @Autowired
    private RoomTypeService service;

    @GetMapping
    public ResponseEntity<List<RoomType>> getAll() {
        return ResponseEntity.ok(service.getAllTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoomType> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<RoomType> create(@Valid @RequestBody RoomType roomType) {
        return ResponseEntity.ok(service.create(roomType));
    }
}