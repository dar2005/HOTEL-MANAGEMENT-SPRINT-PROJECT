package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room) {

        if (room.getRoomNumber() <= 0) {
            throw new BadRequestException(
                "Invalid room number"
            );
        }

        boolean exists =
            roomRepository.existsByRoomNumber(
                room.getRoomNumber()
            );

        if (exists) {
            throw new ConflictException(
                "Room number already exists"
            );
        }

        return roomRepository.save(room);
    }

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
