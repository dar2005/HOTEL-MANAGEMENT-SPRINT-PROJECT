package com.cg.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.PaymentRequestDTO;
import com.cg.dto.PaymentResponseDTO;
import com.cg.entity.Payment;
import com.cg.entity.Reservation;
import com.cg.repo.PaymentRepository;
import com.cg.repo.ReservationRepository;
import com.cg.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // 🔹 Make Payment
    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentStatus(dto.getPaymentStatus());

        Payment saved = paymentRepository.save(payment);

        return mapToDTO(saved);
    }

    // 🔹 Get Payment by ID
    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToDTO(payment);
    }

    // 🔹 Get Payments by Status
    @Override
    public List<PaymentResponseDTO> getPaymentsByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Get Payments by Reservation
    @Override
    public List<PaymentResponseDTO> getPaymentsByReservation(Long reservationId) {
        return paymentRepository.findByReservation_ReservationId(reservationId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 🔄 Mapper Method
    private PaymentResponseDTO mapToDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentStatus(payment.getPaymentStatus());
        return dto;
    }
}
