package com.cg.service;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;
import com.cg.entity.Reservation;
import com.cg.entity.Review;
import com.cg.repo.ReservationRepository;
import com.cg.repo.ReviewRepository;
import com.cg.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private com.cg.repo.ReservationRepository reservationRepository;


    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReservation(reservation);
        review.setReviewDate(LocalDate.now());

        return mapToDTO(reviewRepository.save(review));
    }


    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

 
    @Override
    public ReviewResponseDTO getReviewById(Long id) {

        Review review = reviewRepository.findById(id.intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        return mapToDTO(review);
    }


    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto) {

        Review review = reviewRepository.findById(id.intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return mapToDTO(reviewRepository.save(review));
    }

   
    @Override
    public String deleteReview(Long id) {

        Review review = reviewRepository.findById(id.intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        reviewRepository.delete(review);

        return "Review deleted successfully";
    }

 
    @Override
    public List<ReviewResponseDTO> getByReservation(Long reservationId) {
        return reviewRepository.findByReservationReservationId(reservationId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<ReviewResponseDTO> getByRating(Integer rating) {
        return reviewRepository.findByRating(rating)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

 
    @Override
    public List<ReviewResponseDTO> searchByComment(String keyword) {
        return reviewRepository.findByCommentContaining(keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

  
    @Override
    public List<ReviewResponseDTO> getLatestReviews() {
        return reviewRepository.findAllByOrderByReviewDateDesc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

 
    @Override
    public Double getAverageRating() {
        return reviewRepository.findAll()
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

   
    private ReviewResponseDTO mapToDTO(Review review) {
        return new ReviewResponseDTO(
                review.getReviewId(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate()
        );
    }
}
