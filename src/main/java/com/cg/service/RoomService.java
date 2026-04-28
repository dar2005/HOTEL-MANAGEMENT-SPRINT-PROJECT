package com.cg.service;

import com.cg.entity.Room;
import java.util.List;

public interface RoomService {

  
    Room createRoom(Room room, Long typeId);

    List<Room> getAllRooms();

    Room getById(Long id); 

    List<Room> getAvailableRooms();

    List<Room> getUnavailableRooms();

    List<Room> getRoomsByType(String typeName);

    List<Room> getRoomsByPrice(double min, double max);

    Room getByRoomNumber(int roomNumber);

    // UPDATE
    Room updateAvailability(Long id, boolean status); 

    // DELETE
    void deleteRoom(Long id);
}