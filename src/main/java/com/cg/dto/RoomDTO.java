package com.cg.dto;

import jakarta.validation.constraints.*;

public class RoomDTO {

    @NotNull(message = "Room ID is required")
    private Long roomId;

    @Min(value = 1, message = "Room number must be positive")
    private int roomNumber;

    private boolean available;

    @NotNull(message = "RoomType ID is required")
    private Long roomTypeId;

    private String typeName;
    private double pricePerNight;

    public RoomDTO() {}

    public RoomDTO(Long roomId, int roomNumber, boolean available,
                   Long roomTypeId, String typeName, double pricePerNight) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.available = available;
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.pricePerNight = pricePerNight;
    }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Long getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
}