package com.cg.service;

import com.cg.dto.PaymentRequestDTO;
import com.cg.dto.PaymentResponseDTO;
import com.cg.entity.Payment;
import com.cg.entity.Reservation;
import com.cg.repo.PaymentRepository;
import com.cg.repo.ReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private Reservation reservation;

    @BeforeEach
    void setup() {
        reservation = new Reservation();
        reservation.setReservationId(1L);

        payment = new Payment();
        payment.setPaymentId(1L);
        payment.setAmount(1000.0);
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDate.now());
        payment.setReservation(reservation);
    }

    // 🔹 1. makePayment SUCCESS
    @Test
    void testMakePayment_success() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setReservationId(1L);
        dto.setAmount(1000.0);
        dto.setPaymentStatus("SUCCESS");
        dto.setPaymentDate(LocalDate.now());

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        PaymentResponseDTO result = paymentService.makePayment(dto);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getPaymentStatus());

        verify(paymentRepository).save(any(Payment.class));
    }

    // 🔹 2. makePayment FAIL
    @Test
    void testMakePayment_reservationNotFound() {
        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setReservationId(99L);

        when(reservationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> paymentService.makePayment(dto));
    }

    // 🔹 3. getPaymentById SUCCESS
    @Test
    void testGetPaymentById_success() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        PaymentResponseDTO result = paymentService.getPaymentById(1L);

        assertEquals(1000.0, result.getAmount());
    }

    // 🔹 4. getPaymentById FAIL
    @Test
    void testGetPaymentById_notFound() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> paymentService.getPaymentById(1L));
    }

    // 🔹 5. getAllPayments
    @Test
    void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(List.of(payment));

        List<PaymentResponseDTO> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
    }

    // 🔹 6. deletePayment
    @Test
    void testDeletePayment() {
        doNothing().when(paymentRepository).deleteById(1L);

        paymentService.deletePayment(1L);

        verify(paymentRepository, times(1)).deleteById(1L);
    }

    // 🔹 7. updatePaymentStatus SUCCESS
    @Test
    void testUpdatePaymentStatus_success() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        PaymentResponseDTO result =
                paymentService.updatePaymentStatus(1L, "FAILED");

        assertEquals("FAILED", result.getPaymentStatus());
    }

    // 🔹 8. updatePaymentStatus FAIL
    @Test
    void testUpdatePaymentStatus_notFound() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> paymentService.updatePaymentStatus(1L, "FAILED"));
    }

    // 🔹 9. getPaymentsByStatus
    @Test
    void testGetPaymentsByStatus() {
        when(paymentRepository.findByPaymentStatus("SUCCESS"))
                .thenReturn(List.of(payment));

        List<PaymentResponseDTO> result =
                paymentService.getPaymentsByStatus("SUCCESS");

        assertEquals(1, result.size());
    }

    // 🔹 10. getPaymentsByReservation
    @Test
    void testGetPaymentsByReservation() {
        when(paymentRepository.findByReservation_ReservationId(1L))
                .thenReturn(List.of(payment));

        List<PaymentResponseDTO> result =
                paymentService.getPaymentsByReservation(1L);

        assertEquals(1, result.size());
    }

    // 🔹 11. countPayments
    @Test
    void testCountPayments() {
        when(paymentRepository.count()).thenReturn(5L);

        long count = paymentService.countPayments();

        assertEquals(5, count);
    }

    // 🔹 12. existsById
    @Test
    void testExistsById() {
        when(paymentRepository.existsById(1L)).thenReturn(true);

        boolean exists = paymentService.existsById(1L);

        assertTrue(exists);
    }

    // 🔹 13. getSuccessfulPayments
    @Test
    void testGetSuccessfulPayments() {
        when(paymentRepository.findByPaymentStatus("SUCCESS"))
                .thenReturn(List.of(payment));

        List<PaymentResponseDTO> result =
                paymentService.getSuccessfulPayments();

        assertEquals(1, result.size());
    }
}