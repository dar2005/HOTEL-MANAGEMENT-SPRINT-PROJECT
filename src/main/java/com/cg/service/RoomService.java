package com.cg.service;

import com.cg.entity.Room;
import com.cg.entity.RoomType;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomRepository;
import com.cg.repo.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomTypeRepository typeRepo;

    // CREATE ROOM
    public Room createRoom(Room room, Long typeId) {

        if (roomRepo.existsById(room.getRoomId())) {
            throw new RuntimeException("Room ID already exists: " + room.getRoomId());
        }

        RoomType type = typeRepo.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + typeId));

        room.setRoomType(type);
        return roomRepo.save(room);
    }

    // GET ALL
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    // AVAILABLE
    public List<Room> getAvailableRooms() {
        return roomRepo.findByIsAvailableTrue();
    }

    // UNAVAILABLE
    public List<Room> getUnavailableRooms() {
        return roomRepo.findByIsAvailableFalse();
    }

    // BY TYPE
    public List<Room> getRoomsByType(String typeName) {
        return roomRepo.findByRoomType_TypeName(typeName);
    }

    // BY PRICE
    public List<Room> getRoomsByPrice(double min, double max) {
        return roomRepo.findByRoomType_PricePerNightBetween(min, max);
    }

    // BY ROOM NUMBER
    public List<Room> getByRoomNumber(int roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }

    // DELETE
    public void deleteRoom(Long id) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + id));

        roomRepo.delete(room);
    }
}