package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.entity.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    Amenity findByName(String name);
}
