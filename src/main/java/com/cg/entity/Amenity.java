package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "amenity")
public class Amenity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer amenityId;

    private String name;
    private String description;


    public Integer getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Integer amenityId) {
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