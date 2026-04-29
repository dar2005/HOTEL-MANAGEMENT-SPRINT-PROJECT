// ReservationServiceImplTest.java
package com.cg.service;

import com.cg.entity.Reservation;
import com.cg.entity.Room;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.ReservationRepository;
import com.cg.repo.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setRoomId(1L);

        reservation = new Reservation();
        reservation.setReservationId(1L);
        reservation.setGuestName("Priyanka");
        reservation.setGuestEmail("priyanka@gmail.com");
        reservation.setGuestPhone("9876543210");
        reservation.setCheckInDate(LocalDate.of(2026, 5, 1));
        reservation.setCheckOutDate(LocalDate.of(2026, 5, 5));
        reservation.setRoom(room);
    }

    @Test
    void testCreateReservation() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.createReservation(reservation);

        assertNotNull(result);
        assertEquals("Priyanka", result.getGuestName());
        verify(roomRepository).findById(1L);
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testGetAllReservations() {
        when(reservationRepository.findAll())
                .thenReturn(Arrays.asList(reservation));

        List<Reservation> list = reservationService.getAllReservations();

        assertEquals(1, list.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void testGetReservationById() {
        when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationById(1L);

        assertEquals(1L, result.getReservationId());
    }

    @Test
    void testGetReservationById_NotFound() {
        when(reservationRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reservationService.getReservationById(1L));
    }

    @Test
    void testUpdateReservation() {
        when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(reservation));

        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(reservation);

        Reservation updated = new Reservation();
        updated.setGuestName("Ram");
        updated.setGuestEmail("ram@gmail.com");
        updated.setGuestPhone("9999999999");
        updated.setCheckInDate(LocalDate.of(2026, 6, 1));
        updated.setCheckOutDate(LocalDate.of(2026, 6, 5));
        updated.setRoom(room);

        Reservation result =
                reservationService.updateReservation(1L, updated);

        assertEquals("Ram", result.getGuestName());
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void testDeleteReservation() {
        when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(reservation));

        reservationService.deleteReservation(1L);

        verify(reservationRepository).delete(reservation);
    }

    @Test
    void testGetReservationsByGuestName() {
        when(reservationRepository.findByGuestName("Priyanka"))
                .thenReturn(List.of(reservation));

        List<Reservation> result =
                reservationService.getReservationsByGuestName("Priyanka");

        assertEquals(1, result.size());
    }

    @Test
    void testGetReservationsByCheckInDate() {
        LocalDate date = LocalDate.of(2026, 5, 1);

        when(reservationRepository.findByCheckInDate(date))
                .thenReturn(List.of(reservation));

        List<Reservation> result =
                reservationService.getReservationsByCheckInDate(date);

        assertEquals(1, result.size());
    }

    @Test
    void testGetReservationsByRoomId() {
        when(reservationRepository.findByRoomRoomId(1L))
                .thenReturn(List.of(reservation));

        List<Reservation> result =
                reservationService.getReservationsByRoomId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testGetTotalReservations() {
        when(reservationRepository.count()).thenReturn(5L);

        long count = reservationService.getTotalReservations();

        assertEquals(5L, count);
    }

    @Test
    void testExistsReservation() {
        when(reservationRepository.existsById(1L))
                .thenReturn(true);

        boolean result = reservationService.existsReservation(1L);

        assertTrue(result);
    }
}