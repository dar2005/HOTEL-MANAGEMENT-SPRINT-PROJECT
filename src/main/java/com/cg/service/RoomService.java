package com.cg.service;

import com.cg.entity.Room;
import java.util.List;

public interface RoomService {

    Room createRoom(Room room, Long typeId);

    List<Room> getAllRooms();

    List<Room> getAvailableRooms();

    List<Room> getUnavailableRooms();

    List<Room> getRoomsByType(String typeName);

    List<Room> getRoomsByPrice(double min, double max);

    List<Room> getByRoomNumber(int roomNumber);

    void deleteRoom(Long id);
}