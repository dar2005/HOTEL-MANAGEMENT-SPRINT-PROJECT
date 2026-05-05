package com.cg.controller;

import com.cg.entity.Reservation;
import com.cg.service.ReservationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping
    public Reservation createReservation( @Valid
            @RequestBody Reservation reservation,
            Principal principal) {

        return reservationService
                .createReservation(reservation, principal.getName());
    }
   
    @GetMapping
    public List<Reservation> getAllReservations() {

        return reservationService
                .getAllReservations();
    }

    @GetMapping("/my")
    public List<Reservation> getMyReservations(Principal principal) {

        return reservationService
                .getReservationsForUser(principal.getName());
    }

    @GetMapping("/my/{id}")
    public Reservation getMyReservationById(
            @PathVariable Long id,
            Principal principal) {

        return reservationService
                .getReservationForUser(id, principal.getName());
    }
    
    @GetMapping("/{id}")
    public Reservation getReservationById(
            @PathVariable Long id) {

        return reservationService
                .getReservationById(id);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable Long id, @Valid
            @RequestBody Reservation reservation) {

        return reservationService
                .updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(
            @PathVariable Long id) {

        reservationService.deleteReservation(id);

        return "Reservation deleted successfully";
    }

    @GetMapping("/guest/{guestName}")
    public List<Reservation> getByGuestName(
            @PathVariable String guestName) {

        return reservationService
                .getReservationsByGuestName(guestName);
    }

    @GetMapping("/checkin/{date}")
    public List<Reservation> getByCheckInDate(
            @PathVariable String date) {

        return reservationService
                .getReservationsByCheckInDate(
                        LocalDate.parse(date));
    }

    @GetMapping("/room/{roomId}")
    public List<Reservation> getByRoomId(
            @PathVariable Long roomId) {

        return reservationService
                .getReservationsByRoomId(roomId);
    }

    @GetMapping("/count")
    public long getTotalReservations() {

        return reservationService
                .getTotalReservations();
    }

    @GetMapping("/exists/{id}")
    public boolean existsReservation(
            @PathVariable Long id) {

        return reservationService
                .existsReservation(id);
    }
}