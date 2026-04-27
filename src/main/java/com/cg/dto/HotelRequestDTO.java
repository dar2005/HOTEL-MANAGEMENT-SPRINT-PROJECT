package com.cg.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class HotelRequestDTO {
	
	 @NotBlank(message = "Hotel name is required")
	 @Size(min = 3, max = 50)
    private String name;
    private String location;
    private String description;
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
    
}