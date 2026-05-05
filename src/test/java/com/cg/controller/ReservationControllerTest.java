package com.cg.controller;

import com.cg.entity.Reservation;
import com.cg.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation();
        Principal principal = () -> "shubh";

        when(reservationService.createReservation(reservation, "shubh")).thenReturn(reservation);

        Reservation result = reservationController.createReservation(reservation, principal);

        assertEquals(reservation, result);
        verify(reservationService, times(1)).createReservation(reservation, "shubh");
    }

    @Test
    void testGetAllReservations() {
        List<Reservation> list = Arrays.asList(new Reservation(), new Reservation());
        when(reservationService.getAllReservations()).thenReturn(list);

        List<Reservation> result = reservationController.getAllReservations();

        assertEquals(2, result.size());
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void testGetMyReservations() {
        Principal principal = () -> "shubh";
        List<Reservation> list = List.of(new Reservation());

        when(reservationService.getReservationsForUser("shubh")).thenReturn(list);

        List<Reservation> result = reservationController.getMyReservations(principal);

        assertEquals(1, result.size());
        verify(reservationService).getReservationsForUser("shubh");
    }

    @Test
    void testGetMyReservationById() {
        Principal principal = () -> "shubh";
        Reservation reservation = new Reservation();

        when(reservationService.getReservationForUser(1L, "shubh")).thenReturn(reservation);

        Reservation result = reservationController.getMyReservationById(1L, principal);

        assertEquals(reservation, result);
        verify(reservationService).getReservationForUser(1L, "shubh");
    }

    @Test
    void testGetReservationById() {
        Reservation reservation = new Reservation();
        when(reservationService.getReservationById(1L)).thenReturn(reservation);

        Reservation result = reservationController.getReservationById(1L);

        assertEquals(reservation, result);
        verify(reservationService).getReservationById(1L);
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = new Reservation();
        when(reservationService.updateReservation(1L, reservation)).thenReturn(reservation);

        Reservation result = reservationController.updateReservation(1L, reservation);

        assertEquals(reservation, result);
        verify(reservationService).updateReservation(1L, reservation);
    }

    @Test
    void testDeleteReservation() {
        String result = reservationController.deleteReservation(1L);

        assertEquals("Reservation deleted successfully", result);
        verify(reservationService).deleteReservation(1L);
    }

    @Test
    void testGetByGuestName() {
        List<Reservation> list = List.of(new Reservation());
        when(reservationService.getReservationsByGuestName("Ram")).thenReturn(list);

        List<Reservation> result = reservationController.getByGuestName("Ram");

        assertEquals(1, result.size());
    }

    @Test
    void testGetByCheckInDate() {
        LocalDate date = LocalDate.of(2026, 5, 1);
        List<Reservation> list = List.of(new Reservation());

        when(reservationService.getReservationsByCheckInDate(date)).thenReturn(list);

        List<Reservation> result = reservationController.getByCheckInDate("2026-05-01");

        assertEquals(1, result.size());
    }

    @Test
    void testGetByRoomId() {
        List<Reservation> list = List.of(new Reservation());

        when(reservationService.getReservationsByRoomId(1L)).thenReturn(list);

        List<Reservation> result = reservationController.getByRoomId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testGetTotalReservations() {
        when(reservationService.getTotalReservations()).thenReturn(5L);

        long result = reservationController.getTotalReservations();

        assertEquals(5, result);
    }

    @Test
    void testExistsReservation() {
        when(reservationService.existsReservation(1L)).thenReturn(true);

        boolean result = reservationController.existsReservation(1L);

        assertTrue(result);
    }
}
