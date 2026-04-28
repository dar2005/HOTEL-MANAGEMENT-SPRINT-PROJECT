package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;
import com.cg.entity.Amenity;
import com.cg.entity.Room;
import com.cg.repo.AmenityRepository;
import com.cg.repo.RoomRepository;


@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    // 🔹 Create Amenity
    @Override
    public AmenityResponseDTO createAmenity(AmenityRequestDTO dto) {

        Amenity amenity = new Amenity();
        amenity.setAmenityId(dto.getAmenityId());
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
    
    //Update
    @Override
    public AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO dto) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());
        return mapToDTO(amenityRepository.save(amenity));
    }
    
    // 🔹 Delete
    @Override
    public void deleteAmenity(Long id) {
        amenityRepository.deleteById(id);
    }
    
    //Get By Name
    @Override
    public AmenityResponseDTO getAmenityByName(String name) {
        Amenity amenity = amenityRepository.findByName(name);
        if (amenity == null) throw new RuntimeException("Amenity not found");
        return mapToDTO(amenity);
    }
    
    //Exists
    @Override
    public boolean existsById(Long id) {
        return amenityRepository.existsById(id);
    }
    
    //Count amenities
    @Override
    public long countAmenities() {
        return amenityRepository.count();
    }
    
    
    //assign to room
    @Override
    public void assignAmenityToRoom(Long roomId, Long amenityId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow();
        room.getAmenities().add(amenity);
        roomRepository.save(room);
    }
    
    //remove from room
    @Override
    public void removeAmenityFromRoom(Long roomId, Long amenityId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow();
        room.getAmenities().remove(amenity);
        roomRepository.save(room);
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
