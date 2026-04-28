package com.cg.service;

import com.cg.dto.HotelRequestDTO;
import com.cg.dto.HotelResponseDTO;
import com.cg.entity.Hotel;
import com.cg.repo.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO createHotel(Long id, HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(id);
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotel.setDescription(dto.getDescription());
        Hotel saved = hotelRepository.save(hotel);
        return mapToDTO(saved);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return mapToDTO(hotel);
    }

    @Override
    public List<HotelResponseDTO> searchByLocation(String location) {
        return hotelRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelResponseDTO> searchByName(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelResponseDTO> searchByLocationAndName(String location, String name) {
        return hotelRepository
                .findByLocationContainingIgnoreCaseAndNameContainingIgnoreCase(location, name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotel.setDescription(dto.getDescription());
        Hotel updated = hotelRepository.save(hotel);
        return mapToDTO(updated);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    private HotelResponseDTO mapToDTO(Hotel hotel) {
        return new HotelResponseDTO(
                hotel.getHotelId(),
                hotel.getName(),
                hotel.getLocation(),
                hotel.getDescription()
        );
    }
}