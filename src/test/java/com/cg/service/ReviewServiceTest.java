package com.cg.service;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;
import com.cg.entity.Reservation;
import com.cg.entity.Review;
import com.cg.repo.ReservationRepository;
import com.cg.repo.ReviewRepository;
import com.cg.exception.ResourceNotFoundException;

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
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private Reservation reservation;

    @BeforeEach
    void setup() {
        reservation = new Reservation();
        reservation.setReservationId(1L);

        review = new Review();
        review.setReviewId(1L);
        review.setRating(5);
        review.setComment("Excellent");
        review.setReviewDate(LocalDate.now());
        review.setReservation(reservation);
    }

    // ✅ CREATE
    @Test
    void createReview_success() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Excellent", 1L);

        when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(reservation));
        when(reviewRepository.save(any(Review.class)))
                .thenReturn(review);

        ReviewResponseDTO response = reviewService.createReview(dto);

        assertEquals(5, response.getRating());
        assertEquals("Excellent", response.getComment());
    }

    @Test
    void createReview_reservationNotFound() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Test", 1L);

        when(reservationRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            reviewService.createReview(dto);
        });
    }

    // ✅ GET BY ID
    @Test
    void getReviewById_success() {
        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        ReviewResponseDTO response = reviewService.getReviewById(1L);

        assertEquals("Excellent", response.getComment());
    }

    @Test
    void getReviewById_notFound() {
        when(reviewRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reviewService.getReviewById(1L);
        });
    }

    // ✅ UPDATE
    @Test
    void updateReview_success() {
        ReviewRequestDTO dto = new ReviewRequestDTO(4, "Good", 1L);

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class)))
                .thenReturn(review);

        ReviewResponseDTO response = reviewService.updateReview(1L, dto);

        assertEquals(4, response.getRating());
        assertEquals("Good", response.getComment());
    }

    // ✅ DELETE
    @Test
    void deleteReview_success() {
        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        String result = reviewService.deleteReview(1L);

        assertEquals("Review deleted successfully", result);
        verify(reviewRepository).delete(review);
    }

    // ✅ GET ALL
    @Test
    void getAllReviews() {
        when(reviewRepository.findAll())
                .thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getAllReviews();

        assertEquals(1, list.size());
    }

    // ✅ FILTERS
    @Test
    void getByRating() {
        when(reviewRepository.findByRating(5))
                .thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getByRating(5);

        assertEquals(1, list.size());
    }

    @Test
    void searchByComment() {
        when(reviewRepository.findByCommentContaining("Exc"))
                .thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.searchByComment("Exc");

        assertEquals(1, list.size());
    }

    @Test
    void getLatestReviews() {
        when(reviewRepository.findAllByOrderByReviewDateDesc())
                .thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getLatestReviews();

        assertEquals(1, list.size());
    }

    // ✅ AVERAGE
    @Test
    void getAverageRating() {
        when(reviewRepository.findAll())
                .thenReturn(List.of(review));

        Double avg = reviewService.getAverageRating();

        assertEquals(5.0, avg);
    }
}
