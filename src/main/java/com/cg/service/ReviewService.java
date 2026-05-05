package com.cg.service;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO createReview(ReviewRequestDTO dto, String username);

    List<ReviewResponseDTO> getAllReviews();

    ReviewResponseDTO getReviewById(Long id);

    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto);

    String deleteReview(Long id);

    List<ReviewResponseDTO> getByReservation(Long reservationId);

    List<ReviewResponseDTO> getByRating(Integer rating);

    List<ReviewResponseDTO> searchByComment(String keyword);

    List<ReviewResponseDTO> getLatestReviews();

    Double getAverageRating();
}
