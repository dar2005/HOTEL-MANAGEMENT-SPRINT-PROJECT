package com.cg.service;

import com.cg.entity.RoomType;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository repo;

    @Override
    public List<RoomType> getAllTypes() {
        return repo.findAll();
    }

    @Override
    public RoomType getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + id));
    }

    @Override
    public RoomType getByName(String name) {
        return repo.findByTypeName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with name: " + name));
    }

    @Override
    public RoomType create(RoomType rt) {

        if (repo.findByTypeName(rt.getTypeName()).isPresent()) {
        	throw new ConflictException("RoomType already exists: " + rt.getTypeName());
        }

        return repo.save(rt);
    }
}