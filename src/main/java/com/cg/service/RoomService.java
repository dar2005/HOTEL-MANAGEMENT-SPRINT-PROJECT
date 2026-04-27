package com.cg.service;

import com.cg.entity.Room;
import com.cg.entity.RoomType;
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

    public Room createRoom(Room room, Long typeId) {

        if (roomRepo.existsById(room.getRoomId())) {
            throw new RuntimeException("Room ID already exists");
        }

        RoomType type = typeRepo.findById(typeId)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));

        room.setRoomType(type);
        return roomRepo.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public List<Room> getAvailableRooms() {
        return roomRepo.findByIsAvailableTrue();
    }

    public List<Room> getUnavailableRooms() {
        return roomRepo.findByIsAvailableFalse();
    }

    public List<Room> getRoomsByType(String typeName) {
        return roomRepo.findByRoomType_TypeName(typeName);
    }

    public List<Room> getRoomsByPrice(double min, double max) {
        return roomRepo.findByRoomType_PricePerNightBetween(min, max);
    }

    public List<Room> getByRoomNumber(int roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }

    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}