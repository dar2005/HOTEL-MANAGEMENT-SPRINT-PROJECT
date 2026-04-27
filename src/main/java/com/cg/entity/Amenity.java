package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "amenity")
public class Amenity {

    @Id
    private Integer amenityId;

    private String name;
    private String description;

    // Getters and Setters
}