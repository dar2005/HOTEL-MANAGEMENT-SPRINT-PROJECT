package com.cg.service;

import com.cg.entity.Reservation;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {

        Reservation oldReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        oldReservation.setGuestName(reservation.getGuestName());
        oldReservation.setGuestEmail(reservation.getGuestEmail());
        oldReservation.setGuestPhone(reservation.getGuestPhone());
        oldReservation.setCheckInDate(reservation.getCheckInDate());
        oldReservation.setCheckOutDate(reservation.getCheckOutDate());
        oldReservation.setRoom(reservation.getRoom());

        return reservationRepository.save(oldReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getReservationsByGuestName(String guestName) {
        return reservationRepository.findByGuestName(guestName);
    }

    @Override
    public List<Reservation> getReservationsByCheckInDate(LocalDate checkInDate) {
        return reservationRepository.findByCheckInDate(checkInDate);
    }

    @Override
    public List<Reservation> getReservationsByRoomId(Long roomId) {
        return reservationRepository.findByRoomRoomId(roomId);
    }

    @Override
    public long getTotalReservations() {
        return reservationRepository.count();
    }

    @Override
    public boolean existsReservation(Long id) {
        return reservationRepository.existsById(id);
    }
}
