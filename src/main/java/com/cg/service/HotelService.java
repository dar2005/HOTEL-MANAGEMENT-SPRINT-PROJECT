package com.cg.service;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel createHotel(Hotel hotel) {

        if (hotel.getName() == null || hotel.getName().isBlank()) {
            throw new ValidationException("Hotel name is required");
        }

        boolean exists =
            hotelRepository.existsByName(hotel.getName());

        if (exists) {
            throw new ConflictException("Hotel already exists");
        }

        return hotelRepository.save(hotel);
    }

    public Hotel getHotelById(Long id) {

        return hotelRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Hotel not found with id " + id
                ));
    }

    public Hotel updateHotel(Long id, Hotel hotel) {

        Hotel existing = getHotelById(id);

        existing.setName(hotel.getName());
        existing.setLocation(hotel.getLocation());

        return hotelRepository.save(existing);
    }

    public void deleteHotel(Long id) {

        Hotel hotel = getHotelById(id);

        hotelRepository.delete(hotel);
    }
}
=======
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
>>>>>>> main
