package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public Amenity save(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public List<Amenity> getAll() {
        return amenityRepository.findAll();
    }
}
