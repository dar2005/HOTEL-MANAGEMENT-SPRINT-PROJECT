package com.cg.service;

import java.util.List;
import com.cg.dto.AmenityRequestDTO;
import com.cg.dto.AmenityResponseDTO;

public interface AmenityService {

    AmenityResponseDTO createAmenity(AmenityRequestDTO dto);

    List<AmenityResponseDTO> getAllAmenities();

    AmenityResponseDTO getAmenityById(Long id);

    void deleteAmenity(Long id);
}