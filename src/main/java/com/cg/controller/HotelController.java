package com.cg.controller;

import com.cg.dto.HotelRequestDTO;
import com.cg.dto.HotelResponseDTO;
import com.cg.service.HotelService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> createHotel(
            @PathVariable Long id,
            @Valid @RequestBody HotelRequestDTO dto) {
        return ResponseEntity.ok(hotelService.createHotel(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<HotelResponseDTO>> searchByLocation(
            @RequestParam String location) {
        return ResponseEntity.ok(hotelService.searchByLocation(location));
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<HotelResponseDTO>> searchByName(
            @RequestParam String name) {
        return ResponseEntity.ok(hotelService.searchByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponseDTO>> searchByLocationAndName(
            @RequestParam String location,
            @RequestParam String name) {
        return ResponseEntity.ok(
                hotelService.searchByLocationAndName(location, name)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(
            @PathVariable Long id,
            @Valid @RequestBody HotelRequestDTO dto) {
        return ResponseEntity.ok(hotelService.updateHotel(id, dto));
    }
}