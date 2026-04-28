package com.cg.controller;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;
import com.cg.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 1. CREATE REVIEW
    @PostMapping
    public ReviewResponseDTO createReview(@RequestBody ReviewRequestDTO dto) {
        return reviewService.createReview(dto);
    }

    // 2. GET ALL REVIEWS
    @GetMapping
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 3. GET REVIEW BY ID
    @GetMapping("/{id}")
    public ReviewResponseDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // 4. UPDATE REVIEW
    @PutMapping("/{id}")
    public ReviewResponseDTO updateReview(@PathVariable Long id,
                                          @RequestBody ReviewRequestDTO dto) {
        return reviewService.updateReview(id, dto);
    }

    // 5. DELETE REVIEW
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id);
    }

    // 6. GET BY RESERVATION
    @GetMapping("/reservation/{reservationId}")
    public List<ReviewResponseDTO> getByReservation(@PathVariable Long reservationId) {
        return reviewService.getByReservation(reservationId);
    }

    // 7. GET BY RATING
    @GetMapping("/rating/{rating}")
    public List<ReviewResponseDTO> getByRating(@PathVariable Integer rating) {
        return reviewService.getByRating(rating);
    }

    // 8. SEARCH BY COMMENT
    @GetMapping("/search")
    public List<ReviewResponseDTO> searchByComment(@RequestParam String keyword) {
        return reviewService.searchByComment(keyword);
    }

    // 9. GET LATEST REVIEWS
    @GetMapping("/latest")
    public List<ReviewResponseDTO> getLatestReviews() {
        return reviewService.getLatestReviews();
    }

    // 10. GET AVERAGE RATING
    @GetMapping("/average")
    public Double getAverageRating() {
        return reviewService.getAverageRating();
    }
}
