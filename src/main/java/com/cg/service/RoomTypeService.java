package com.cg.service;

<<<<<<< HEAD
=======
import com.cg.entity.RoomType;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomTypeRepository;
>>>>>>> main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

<<<<<<< HEAD
    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public List<RoomType> getAll() {
        return roomTypeRepository.findAll();
=======
    // GET ALL
    public List<RoomType> getAllTypes() {
        return repo.findAll();
    }

    // GET BY ID
    public RoomType getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + id));
    }

    // GET BY NAME
    public RoomType getByName(String name) {
        return repo.findByTypeName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with name: " + name));
    }

    // CREATE
    public RoomType create(RoomType rt) {

        if (repo.findByTypeName(rt.getTypeName()).isPresent()) {
            throw new RuntimeException("RoomType already exists: " + rt.getTypeName());
        }

        return repo.save(rt);
>>>>>>> main
    }
}