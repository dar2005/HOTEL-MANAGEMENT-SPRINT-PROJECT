package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HotelRequestDTO {

    @NotBlank(message = "Hotel name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
	 
	 @NotBlank(message = "Location is required")

    @NotBlank(message = "Location is required")
    private String location;

    @Size(max = 200, message = "Description can be max 200 characters")
    private String description;

    public HotelRequestDTO() {}

    public HotelRequestDTO(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
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
}