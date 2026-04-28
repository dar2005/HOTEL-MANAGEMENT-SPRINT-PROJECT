package com.cg.service;

import com.cg.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

    List<Reservation> getReservationsByGuestName(String guestName);

    List<Reservation> getReservationsByCheckInDate(LocalDate checkInDate);

    List<Reservation> getReservationsByRoomId(Long roomId);

    long getTotalReservations();

    boolean existsReservation(Long id);
}
