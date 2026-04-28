package com.cg.dto;

import jakarta.validation.constraints.*;

public class RoomTypeDTO {

    @NotBlank(message = "Type name required")
    private String typeName;

    @NotBlank(message = "Description required")
    private String description;

    @Min(1)
    @Max(10)
    private int maxOccupancy;

    @Positive
    private double pricePerNight;

    // Constructors
    public RoomTypeDTO() {}

    public RoomTypeDTO(String typeName, String description,
                       int maxOccupancy, double pricePerNight) {
        this.typeName = typeName;
        this.description = description;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
    }

    // Getters & Setters
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxOccupancy() { return maxOccupancy; }
    public void setMaxOccupancy(int maxOccupancy) { this.maxOccupancy = maxOccupancy; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }
}