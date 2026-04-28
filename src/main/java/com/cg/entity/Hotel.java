package com.cg.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(nullable = false)
    private String name;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;
 
    public Hotel() {
		super();
	}

	public Hotel(Long hotelId, String name, String location, String description, List<Amenity> amenities) {
		super();
		this.hotelId = hotelId;
		this.name = name;
		this.location = location;
		this.description = description;
		this.amenities = amenities;
	}
    
	@ManyToMany
	@JsonIgnore
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
	@JsonIgnore
    private List<Amenity> amenities;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}
	
}