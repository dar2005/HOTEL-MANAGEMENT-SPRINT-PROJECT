package com.cg.repo;

import com.cg.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocationContainingIgnoreCase(String location);

    List<Hotel> findByNameContainingIgnoreCase(String name);

    List<Hotel> findByLocationContainingIgnoreCaseAndNameContainingIgnoreCase(String location, String name);
}