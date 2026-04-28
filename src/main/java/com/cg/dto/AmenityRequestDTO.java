package com.cg.dto;

import jakarta.validation.constraints.NotBlank;

public class AmenityRequestDTO {
	private Long amenityId;
    public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	@NotBlank
	private String name;
    private String description;
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
