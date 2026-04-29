package com.cg.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToMany
<<<<<<< Updated upstream
    
=======
>>>>>>> Stashed changes
    @JoinTable(
        name = "room_amenity",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    @JsonIgnore
    private List<Amenity> amenities = new ArrayList<>();

    // Default Constructor
    public Room() {
    }

    // Parameterized Constructor
    public Room(Long roomId, Integer roomNumber, Boolean isAvailable, RoomType roomType) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

    // Getters and Setters

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}