package com.cg.service;

import com.cg.dto.HotelRequestDTO;
import com.cg.dto.HotelResponseDTO;

import java.util.List;

public interface HotelService {

    HotelResponseDTO createHotel(Long id, HotelRequestDTO dto);

    List<HotelResponseDTO> getAllHotels();

    HotelResponseDTO getHotelById(Long id);

    List<HotelResponseDTO> searchByLocation(String location);

    List<HotelResponseDTO> searchByName(String name);

    List<HotelResponseDTO> searchByLocationAndName(String location, String name);

    HotelResponseDTO updateHotel(Long id, HotelRequestDTO dto);

    void deleteHotel(Long id);
}