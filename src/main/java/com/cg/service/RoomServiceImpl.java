package com.cg.service;

import com.cg.entity.Room;
import com.cg.entity.RoomType;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomRepository;
import com.cg.repo.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomTypeRepository typeRepo;

    @Override
    public Room createRoom(Room room, Long typeId) {

        if (roomRepo.existsById(room.getRoomId())) {
        	throw new ConflictException("Room ID already exists: " + room.getRoomId());
        }

        RoomType type = typeRepo.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + typeId));

        room.setRoomType(type);
        return roomRepo.save(room);
    }
//comment
    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public List<Room> getAvailableRooms() {
        return roomRepo.findByIsAvailableTrue();
    }

    @Override
    public List<Room> getUnavailableRooms() {
        return roomRepo.findByIsAvailableFalse();
    }

    @Override
    public List<Room> getRoomsByType(String typeName) {
        return roomRepo.findByRoomType_TypeName(typeName);
    }

    @Override
    public List<Room> getRoomsByPrice(double min, double max) {
        return roomRepo.findByRoomType_PricePerNightBetween(min, max);
    }

    @Override
    public List<Room> getByRoomNumber(int roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }

    @Override
    public void deleteRoom(Long id) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + id));

        roomRepo.delete(room);
    }
}