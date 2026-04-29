//package com.cg.controller;
//
//import com.cg.dto.ReviewRequestDTO;
//import com.cg.dto.ReviewResponseDTO;
//import com.cg.service.ReviewService;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ReviewController.class)
//class ReviewControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ReviewService reviewService;
//
//    // ✅ CREATE
//    @Test
//    void createReview() throws Exception {
//        ReviewResponseDTO response =
//                new ReviewResponseDTO(1L, 5, "Excellent", LocalDate.now());
//
//        when(reviewService.createReview(any()))
//                .thenReturn(response);
//
//        mockMvc.perform(post("/reviews")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"rating\":5,\"comment\":\"Excellent\",\"reservationId\":1}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.rating").value(5));
//    }
//
//    // ✅ GET ALL
//    @Test
//    void getAllReviews() throws Exception {
//        when(reviewService.getAllReviews())
//                .thenReturn(List.of(
//                        new ReviewResponseDTO(1L, 5, "Excellent", LocalDate.now())
//                ));
//
//        mockMvc.perform(get("/reviews"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(1));
//    }
//
//    // ✅ GET BY ID
//    @Test
//    void getReviewById() throws Exception {
//        when(reviewService.getReviewById(1L))
//                .thenReturn(new ReviewResponseDTO(1L, 5, "Excellent", LocalDate.now()));
//
//        mockMvc.perform(get("/reviews/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.comment").value("Excellent"));
//    }
//
//    // ✅ UPDATE
//    @Test
//    void updateReview() throws Exception {
//        when(reviewService.updateReview(eq(1L), any()))
//                .thenReturn(new ReviewResponseDTO(1L, 4, "Good", LocalDate.now()));
//
//        mockMvc.perform(put("/reviews/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"rating\":4,\"comment\":\"Good\",\"reservationId\":1}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.rating").value(4));
//    }
//
//    // ✅ DELETE
//    @Test
//    void deleteReview() throws Exception {
//        when(reviewService.deleteReview(1L))
//                .thenReturn("Review deleted successfully");
//
//        mockMvc.perform(delete("/reviews/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Review deleted successfully"));
//    }
//
//    // ✅ FILTER APIs
//    @Test
//    void getByRating() throws Exception {
//        when(reviewService.getByRating(5))
//                .thenReturn(List.of(new ReviewResponseDTO(1L, 5, "Excellent", LocalDate.now())));
//
//        mockMvc.perform(get("/reviews/rating/5"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void searchByComment() throws Exception {
//        when(reviewService.searchByComment("Exc"))
//                .thenReturn(List.of(new ReviewResponseDTO(1L, 5, "Excellent", LocalDate.now())));
//
//        mockMvc.perform(get("/reviews/search?keyword=Exc"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getAverageRating() throws Exception {
//        when(reviewService.getAverageRating()).thenReturn(5.0);
//
//        mockMvc.perform(get("/reviews/average"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("5.0"));
//    }
//}
