package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;
import com.cg.entity.Amenity;
import com.cg.repo.AmenityRepository;


@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    // 🔹 Create Amenity
    @Override
    public AmenityResponseDTO createAmenity(AmenityRequestDTO dto) {

        Amenity amenity = new Amenity();
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());

        Amenity saved = amenityRepository.save(amenity);

        return mapToDTO(saved);
    }

    // 🔹 Get All Amenities
    @Override
    public List<AmenityResponseDTO> getAllAmenities() {
        return amenityRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Get by ID
    @Override
    public AmenityResponseDTO getAmenityById(Long id) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        return mapToDTO(amenity);
    }

    // 🔹 Delete
    @Override
    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }

    // 🔄 Mapper Method
    private AmenityResponseDTO mapToDTO(Amenity amenity) {
        AmenityResponseDTO dto = new AmenityResponseDTO();
        dto.setAmenityId(amenity.getAmenityId());
        dto.setName(amenity.getName());
        dto.setDescription(amenity.getDescription());
        return dto;
    }
}
