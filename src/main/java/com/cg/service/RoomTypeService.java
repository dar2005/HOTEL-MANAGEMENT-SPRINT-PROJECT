package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public List<RoomType> getAll() {
        return roomTypeRepository.findAll();
    }
}