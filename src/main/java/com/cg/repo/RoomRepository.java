package com.cg.repo;

import com.cg.entity.Room;
import com.cg.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByIsAvailableTrue();

    List<Room> findByIsAvailableFalse();

    List<Room> findByRoomType(RoomType roomType);

    List<Room> findByRoomType_TypeName(String typeName);

    List<Room> findByRoomType_PricePerNightLessThan(Double price);

    List<Room> findByRoomType_PricePerNightBetween(Double min, Double max);

    List<Room> findByRoomNumber(int roomNumber);
}