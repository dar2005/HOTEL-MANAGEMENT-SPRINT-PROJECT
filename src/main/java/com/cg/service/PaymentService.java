package com.cg.service;



import java.util.List;
import com.cg.dto.PaymentRequestDTO;
import com.cg.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO makePayment(PaymentRequestDTO dto);

    PaymentResponseDTO getPaymentById(Long id);

    List<PaymentResponseDTO> getPaymentsByStatus(String status);

    List<PaymentResponseDTO> getPaymentsByReservation(Long reservationId);
}
