package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    // Constructors
    public Room() {}

    public Room(Long roomId, int roomNumber, boolean isAvailable, RoomType roomType) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

    // Getters & Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
}