package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByPaymentStatus(String status);

    List<Payment> findByReservation_ReservationId(Long reservationId);
}