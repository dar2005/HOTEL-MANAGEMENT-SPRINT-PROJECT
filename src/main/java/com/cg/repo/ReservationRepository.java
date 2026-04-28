package com.cg.repo;

import com.cg.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuestName(String guestName);

    List<Reservation> findByCheckInDate(LocalDate checkInDate);

    List<Reservation> findByRoomRoomId(Long roomId);

    List<Reservation> findByGuestEmail(String guestEmail);

    List<Reservation> findByGuestPhone(String guestPhone);

    List<Reservation> findByCheckOutDate(LocalDate checkOutDate);

    List<Reservation> findByGuestNameContainingIgnoreCase(String guestName);

    List<Reservation> findByCheckInDateBetween(LocalDate startDate, LocalDate endDate);
}
