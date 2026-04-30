package com.cg.controller;

import com.cg.dto.PaymentRequestDTO;
import com.cg.dto.PaymentResponseDTO;
import com.cg.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = PaymentController.class)
@TestPropertySource(properties = {
        "spring.main.allow-bean-definition-overriding=true"
})

	
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService service;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔹 1. CREATE
    @Test
    void testCreate() throws Exception {
        PaymentRequestDTO req = new PaymentRequestDTO();
        req.setReservationId(1L);
        req.setAmount(1000.0);
        req.setPaymentStatus("SUCCESS");
        req.setPaymentDate(LocalDate.now());

        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);
        res.setAmount(1000.0);
        res.setPaymentStatus("SUCCESS");

        Mockito.when(service.makePayment(Mockito.any())).thenReturn(res);

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId").value(1))
                .andExpect(jsonPath("$.paymentStatus").value("SUCCESS"));
    }

    // 🔹 2. GET BY ID
    @Test
    void testGetById() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);
        res.setAmount(500.0);

        Mockito.when(service.getPaymentById(1L)).thenReturn(res);

        mockMvc.perform(get("/api/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(500.0));
    }

    // 🔹 3. GET ALL
    @Test
    void testGetAll() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);

        Mockito.when(service.getAllPayments()).thenReturn(List.of(res));

        mockMvc.perform(get("/api/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].paymentId").value(1));
    }

    // 🔹 4. DELETE
    @Test
    void testDelete() throws Exception {
        Mockito.doNothing().when(service).deletePayment(1L);

        mockMvc.perform(delete("/api/payments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        Mockito.verify(service).deletePayment(1L);
    }

    // 🔹 5. UPDATE STATUS
    @Test
    void testUpdateStatus() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentStatus("FAILED");

        Mockito.when(service.updatePaymentStatus(1L, "FAILED")).thenReturn(res);

        mockMvc.perform(put("/api/payments/1/status")
                        .param("status", "FAILED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentStatus").value("FAILED"));
    }

    // 🔹 6. GET BY STATUS
    @Test
    void testByStatus() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);

        Mockito.when(service.getPaymentsByStatus("SUCCESS"))
                .thenReturn(List.of(res));

        mockMvc.perform(get("/api/payments/status")
                        .param("status", "SUCCESS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].paymentId").value(1));
    }

    // 🔹 7. GET BY RESERVATION
    @Test
    void testByReservation() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);

        Mockito.when(service.getPaymentsByReservation(1L))
                .thenReturn(List.of(res));

        mockMvc.perform(get("/api/payments/reservation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].paymentId").value(1));
    }

    // 🔹 8. COUNT
    @Test
    void testCount() throws Exception {
        Mockito.when(service.countPayments()).thenReturn(5L);

        mockMvc.perform(get("/api/payments/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    // 🔹 9. EXISTS
    @Test
    void testExists() throws Exception {
        Mockito.when(service.existsById(1L)).thenReturn(true);

        mockMvc.perform(get("/api/payments/exists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    // 🔹 10. SUCCESS PAYMENTS
    @Test
    void testSuccessPayments() throws Exception {
        PaymentResponseDTO res = new PaymentResponseDTO();
        res.setPaymentId(1L);

        Mockito.when(service.getSuccessfulPayments())
                .thenReturn(List.of(res));

        mockMvc.perform(get("/api/payments/success"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].paymentId").value(1));
    }
}