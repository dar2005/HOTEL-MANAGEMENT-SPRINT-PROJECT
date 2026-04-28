package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;
import com.cg.service.AmenityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService service;

    // 1 Create
    @PostMapping
    public ResponseEntity<AmenityResponseDTO> create(@Valid @RequestBody AmenityRequestDTO dto) {
        return ResponseEntity.ok(service.createAmenity(dto));
    }

    // 2 Get all
    @GetMapping
    public ResponseEntity<List<AmenityResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllAmenities());
    }

    // 3 Get by id
    @GetMapping("/{id}")
    public ResponseEntity<AmenityResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAmenityById(id));
    }

    // 4 Update
    @PutMapping("/{id}")
    public ResponseEntity<AmenityResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody AmenityRequestDTO dto) {
        return ResponseEntity.ok(service.updateAmenity(id, dto));
    }

    // 5 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteAmenity(id);
        return ResponseEntity.ok("Deleted");
    }

    // 6 Search by name
    @GetMapping("/search")
    public ResponseEntity<AmenityResponseDTO> byName(@RequestParam String name) {
        return ResponseEntity.ok(service.getAmenityByName(name));
    }

    // 7 Exists
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    // 8 Count
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.countAmenities());
    }

    // 9 Assign to room
    @PostMapping("/assign/room")
    public ResponseEntity<String> assignToRoom(@RequestParam Long roomId,
                                               @RequestParam Long amenityId) {
        service.assignAmenityToRoom(roomId, amenityId);
        return ResponseEntity.ok("Assigned to room");
    }

    // 10 Remove from room
    @DeleteMapping("/remove/room")
    public ResponseEntity<String> removeFromRoom(@RequestParam Long roomId,
                                                 @RequestParam Long amenityId) {
        service.removeAmenityFromRoom(roomId, amenityId);
        return ResponseEntity.ok("Removed from room");
    }
}