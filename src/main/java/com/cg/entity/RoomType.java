<<<<<<< HEAD
=======
package com.cg.entity;

>>>>>>> main
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roomtype")
public class RoomType {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @Column(name = "room_type_id")
>>>>>>> main
    private Long roomTypeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "description")
    private String description;

    @Column(name = "max_occupancy")
    private int maxOccupancy;

    @Column(name = "price_per_night")
    private double pricePerNight;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;

<<<<<<< HEAD
    public RoomType() {}    
=======
    // Constructors
    public RoomType() {}

    public RoomType(Long roomTypeId, String typeName, String description,
                    int maxOccupancy, double pricePerNight) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.description = description;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
    }

    // Getters & Setters
>>>>>>> main
    public Long getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxOccupancy() { return maxOccupancy; }
    public void setMaxOccupancy(int maxOccupancy) { this.maxOccupancy = maxOccupancy; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}