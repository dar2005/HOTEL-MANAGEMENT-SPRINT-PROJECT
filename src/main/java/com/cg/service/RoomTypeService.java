package com.cg.service;

import com.cg.entity.RoomType;
import com.cg.repo.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository repo;

    public List<RoomType> getAllTypes() {
        return repo.findAll();
    }

    public RoomType getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));
    }

    public RoomType getByName(String name) {
        return repo.findByTypeName(name)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));
    }

    public RoomType create(RoomType rt) {
        return repo.save(rt);
    }
}