package com.cg.service;

<<<<<<< HEAD
=======
import com.cg.entity.Room;
import com.cg.entity.RoomType;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.RoomRepository;
import com.cg.repo.RoomTypeRepository;
>>>>>>> main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room) {

<<<<<<< HEAD
        if (room.getRoomNumber() <= 0) {
            throw new BadRequestException(
                "Invalid room number"
            );
        }

        boolean exists =
            roomRepository.existsByRoomNumber(
                room.getRoomNumber()
            );
=======
    // CREATE ROOM
    public Room createRoom(Room room, Long typeId) {

        if (roomRepo.existsById(room.getRoomId())) {
            throw new RuntimeException("Room ID already exists: " + room.getRoomId());
        }

        RoomType type = typeRepo.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + typeId));
>>>>>>> main

        if (exists) {
            throw new ConflictException(
                "Room number already exists"
            );
        }

        return roomRepository.save(room);
    }

<<<<<<< HEAD
    public Room getRoomById(Long id) {

        return roomRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Room not found"
                ));
    }

    public Room bookRoom(Long id) {

        Room room = getRoomById(id);

        if (!room.isAvailable()) {
            throw new ConflictException(
                "Room already booked"
            );
        }

        room.setAvailable(false);

        return roomRepository.save(room);
    }
}
=======
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
>>>>>>> main
