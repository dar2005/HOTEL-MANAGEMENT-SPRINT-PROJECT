package com.cg.service;

import java.util.List;
import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;

public interface AmenityService {

    AmenityResponseDTO createAmenity(AmenityRequestDTO dto);

    List<AmenityResponseDTO> getAllAmenities();

    AmenityResponseDTO getAmenityById(Long id);

    AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO dto);
    
    void deleteAmenity(Long id);
    
    AmenityResponseDTO getAmenityByName(String name);
    
    boolean existsById(Long id);
    
    long countAmenities();

    void assignAmenityToRoom(Long roomId, Long amenityId);
    
    void removeAmenityFromRoom(Long roomId, Long amenityId);
}