package com.cg.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "amenity")
public class Amenity {

    @Id
    private Long amenityId;

    private String name;
    private String description;
    
    @ManyToMany(mappedBy = "amenities")
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public Amenity() {}
    public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	


}