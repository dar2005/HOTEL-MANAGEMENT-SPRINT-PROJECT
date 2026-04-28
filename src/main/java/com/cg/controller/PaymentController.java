package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.PaymentRequestDTO;
import com.cg.dto.PaymentResponseDTO;
import com.cg.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    // 1 Create
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(service.makePayment(dto));
    }

    // 2 Get by id
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentById(id));
    }

    // 3 Get all
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    // 4 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deletePayment(id);
        return ResponseEntity.ok("Deleted");
    }

    // 5 Update status
    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updateStatus(@PathVariable Long id,
                                                           @RequestParam String status) {
        return ResponseEntity.ok(service.updatePaymentStatus(id, status));
    }

    // 6 By status
    @GetMapping("/status")
    public ResponseEntity<List<PaymentResponseDTO>> byStatus(@RequestParam String status) {
        return ResponseEntity.ok(service.getPaymentsByStatus(status));
    }

    // 7 By reservation
    @GetMapping("/reservation/{id}")
    public ResponseEntity<List<PaymentResponseDTO>> byReservation(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentsByReservation(id));
    }

    // 8 Count
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.countPayments());
    }

    // 9 Exists
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    // 10 Success payments
    @GetMapping("/success")
    public ResponseEntity<List<PaymentResponseDTO>> success() {
        return ResponseEntity.ok(service.getSuccessfulPayments());
    }
}
