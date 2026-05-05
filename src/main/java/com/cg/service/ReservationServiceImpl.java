package com.cg.service;

import com.cg.entity.Reservation;
import com.cg.entity.Room;
import com.cg.entity.User;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.ReservationRepository;
import com.cg.repo.RoomRepository;
import com.cg.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(Reservation reservation, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Long roomId = reservation.getRoom().getRoomId();

        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (Boolean.FALSE.equals(room.getIsAvailable())) {
            throw new ConflictException("Room is already booked");
        }

        reservation.setRoom(room);
        reservation.setGuestEmail(user.getEmail());
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            reservation.setGuestName(user.getUsername());
        }

        Reservation savedReservation = reservationRepository.save(reservation);
        room.setIsAvailable(false);
        roomRepository.save(room);

        return savedReservation;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return reservationRepository.findByGuestEmail(user.getEmail());
    }

    @Override
    public Reservation getReservationForUser(Long id, String username) {
        Reservation reservation = getReservationById(id);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (reservation.getGuestEmail() == null ||
                !reservation.getGuestEmail().equalsIgnoreCase(user.getEmail())) {
            throw new ResourceNotFoundException("Reservation not found");
        }

        return reservation;
    }

    @Override
    public Reservation getReservationById(Long id) {

        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {

        Reservation oldReservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        validateReservation(reservation);

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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        Room room = reservation.getRoom();
        if (room != null) {
            room.setIsAvailable(true);
            roomRepository.save(room);
        }

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

    private void validateReservation(Reservation reservation) {

        if (reservation.getGuestName() == null ||
            reservation.getGuestName().trim().isEmpty()) {

            throw new RuntimeException("Guest name is required");
        }

        if (reservation.getGuestEmail() == null ||
            reservation.getGuestEmail().trim().isEmpty()) {

            throw new RuntimeException("Guest email is required");
        }

        if (reservation.getCheckInDate() == null ||
            reservation.getCheckOutDate() == null) {

            throw new RuntimeException("Check-in and Check-out dates are required");
        }

        if (reservation.getCheckOutDate()
                .isBefore(reservation.getCheckInDate())) {

            throw new RuntimeException(
                    "Check-out date must be after check-in date");
        }
    }
}