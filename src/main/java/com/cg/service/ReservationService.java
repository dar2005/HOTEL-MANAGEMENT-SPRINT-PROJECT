package com.cg.service;

import com.cg.entity.Reservation;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(
            Reservation reservation) {

        return reservationRepository.save(
                reservation);
    }

    public List<Reservation> getAllReservations() {

        return reservationRepository.findAll();
    }


    public Reservation getReservationById(
            Long id) {

        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Reservation not found"));
    }


    public Reservation updateReservation(
            Long id,
            Reservation reservation) {

        Reservation oldReservation =
                reservationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation not found"));

        oldReservation.setGuestName(
                reservation.getGuestName());

        oldReservation.setGuestEmail(
                reservation.getGuestEmail());

        oldReservation.setGuestPhone(
                reservation.getGuestPhone());

        oldReservation.setCheckInDate(
                reservation.getCheckInDate());

        oldReservation.setCheckOutDate(
                reservation.getCheckOutDate());

        oldReservation.setRoom(
                reservation.getRoom());

        return reservationRepository.save(
                oldReservation);
    }

    public String deleteReservation(
            Long id) {

        Reservation reservation =
                reservationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation not found"));

        reservationRepository.delete(
                reservation);

        return "Reservation deleted successfully";
    }
}