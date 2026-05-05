package com.cg.service;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;
import com.cg.entity.Reservation;
import com.cg.entity.Review;
import com.cg.entity.User;
import com.cg.exception.ResourceNotFoundException;
import com.cg.repo.ReservationRepository;
import com.cg.repo.ReviewRepository;
import com.cg.repo.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;
    private Reservation reservation;
    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername("shubh");
        user.setEmail("shubh@gmail.com");

        reservation = new Reservation();
        reservation.setReservationId(1L);
        reservation.setGuestEmail("shubh@gmail.com");

        review = new Review();
        review.setReviewId(1L);
        review.setRating(5);
        review.setComment("Excellent");
        review.setReviewDate(LocalDate.now());
        review.setReservation(reservation);
    }

    @Test
    void createReview_success() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Excellent", 1L);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(userRepository.findByUsername("shubh")).thenReturn(Optional.of(user));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponseDTO response = reviewService.createReview(dto, "shubh");

        assertEquals(5, response.getRating());
        assertEquals("Excellent", response.getComment());
        assertEquals(1L, response.getReservationId());
    }

    @Test
    void createReview_reservationNotFound() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Test", 1L);

        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reviewService.createReview(dto, "shubh"));
    }

    @Test
    void createReview_userNotFound() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Excellent", 1L);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(userRepository.findByUsername("missing")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reviewService.createReview(dto, "missing"));
    }

    @Test
    void createReview_wrongUserReservation() {
        ReviewRequestDTO dto = new ReviewRequestDTO(5, "Excellent", 1L);

        User otherUser = new User();
        otherUser.setUsername("other");
        otherUser.setEmail("other@gmail.com");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(userRepository.findByUsername("other")).thenReturn(Optional.of(otherUser));

        assertThrows(ResourceNotFoundException.class,
                () -> reviewService.createReview(dto, "other"));
    }

    @Test
    void getReviewById_success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewResponseDTO response = reviewService.getReviewById(1L);

        assertEquals("Excellent", response.getComment());
        assertEquals(1L, response.getReservationId());
    }

    @Test
    void getReviewById_notFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reviewService.getReviewById(1L));
    }

    @Test
    void updateReview_success() {
        ReviewRequestDTO dto = new ReviewRequestDTO(4, "Good", 1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponseDTO response = reviewService.updateReview(1L, dto);

        assertEquals(4, response.getRating());
        assertEquals("Good", response.getComment());
    }

    @Test
    void deleteReview_success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        String result = reviewService.deleteReview(1L);

        assertEquals("Review deleted successfully", result);
        verify(reviewRepository).delete(review);
    }

    @Test
    void getAllReviews() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getAllReviews();

        assertEquals(1, list.size());
        assertEquals(1L, list.get(0).getReservationId());
    }

    @Test
    void getByReservation() {
        when(reviewRepository.findByReservationReservationId(1L)).thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getByReservation(1L);

        assertEquals(1, list.size());
        assertEquals(1L, list.get(0).getReservationId());
    }

    @Test
    void getByRating() {
        when(reviewRepository.findByRating(5)).thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getByRating(5);

        assertEquals(1, list.size());
    }

    @Test
    void searchByComment() {
        when(reviewRepository.findByCommentContaining("Exc")).thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.searchByComment("Exc");

        assertEquals(1, list.size());
    }

    @Test
    void getLatestReviews() {
        when(reviewRepository.findAllByOrderByReviewDateDesc()).thenReturn(List.of(review));

        List<ReviewResponseDTO> list = reviewService.getLatestReviews();

        assertEquals(1, list.size());
    }

    @Test
    void getAverageRating() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        Double avg = reviewService.getAverageRating();

        assertEquals(5.0, avg);
    }
}
