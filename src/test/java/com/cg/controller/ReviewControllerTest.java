package com.cg.controller;

import com.cg.dto.ReviewResponseDTO;
import com.cg.security.JwtFilter;
import com.cg.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private JwtFilter jwtFilter;

    @Test
    void createReview() throws Exception {
        when(reviewService.createReview(any(), eq("shubh")))
                .thenReturn(new ReviewResponseDTO(1L, 1L, 5, "Excellent", LocalDate.now()));

        mockMvc.perform(post("/reviews")
                .principal(() -> "shubh")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rating\":5,\"comment\":\"Excellent\",\"reservationId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.reservationId").value(1));
    }

    @Test
    void getAllReviews() throws Exception {
        when(reviewService.getAllReviews())
                .thenReturn(List.of(new ReviewResponseDTO(1L, 1L, 5, "Excellent", LocalDate.now())));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getReviewById() throws Exception {
        when(reviewService.getReviewById(1L))
                .thenReturn(new ReviewResponseDTO(1L, 1L, 5, "Excellent", LocalDate.now()));

        mockMvc.perform(get("/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Excellent"));
    }

    @Test
    void updateReview() throws Exception {
        when(reviewService.updateReview(eq(1L), any()))
                .thenReturn(new ReviewResponseDTO(1L, 1L, 4, "Good", LocalDate.now()));

        mockMvc.perform(put("/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rating\":4,\"comment\":\"Good\",\"reservationId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4));
    }

    @Test
    void deleteReview() throws Exception {
        when(reviewService.deleteReview(1L))
                .thenReturn("Review deleted successfully");

        mockMvc.perform(delete("/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Review deleted successfully"));
    }

    @Test
    void getAllReviews_emptyList() throws Exception {
        when(reviewService.getAllReviews()).thenReturn(List.of());

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void getByReservation() throws Exception {
        when(reviewService.getByReservation(1L))
                .thenReturn(List.of(new ReviewResponseDTO(1L, 1L, 5, "Excellent", LocalDate.now())));

        mockMvc.perform(get("/reviews/reservation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].rating").value(5));
    }

    @Test
    void searchByComment() throws Exception {
        when(reviewService.searchByComment("Excellent"))
                .thenReturn(List.of(new ReviewResponseDTO(1L, 1L, 5, "Excellent stay", LocalDate.now())));

        mockMvc.perform(get("/reviews/search").param("keyword", "Excellent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getLatestReviews() throws Exception {
        when(reviewService.getLatestReviews())
                .thenReturn(List.of(new ReviewResponseDTO(1L, 1L, 5, "Latest review", LocalDate.now())));

        mockMvc.perform(get("/reviews/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getAverageRating() throws Exception {
        when(reviewService.getAverageRating()).thenReturn(4.5);

        mockMvc.perform(get("/reviews/average"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.5"));
    }
}
