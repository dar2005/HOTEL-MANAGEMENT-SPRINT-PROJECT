package com.cg.controller;

import com.cg.dto.ReviewRequestDTO;
import com.cg.dto.ReviewResponseDTO;
import com.cg.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    
    @PostMapping
    public ReviewResponseDTO createReview(@RequestBody ReviewRequestDTO dto) {
        return reviewService.createReview(dto);
    }

    
    @GetMapping
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    
    @GetMapping("/{id}")
    public ReviewResponseDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    
    @PutMapping("/{id}")
    public ReviewResponseDTO updateReview(@PathVariable Long id,
                                          @RequestBody ReviewRequestDTO dto) {
        return reviewService.updateReview(id, dto);
    }

    
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id);
    }

    
    @GetMapping("/reservation/{reservationId}")
    public List<ReviewResponseDTO> getByReservation(@PathVariable Long reservationId) {
        return reviewService.getByReservation(reservationId);
    }

    
    @GetMapping("/rating/{rating}")
    public List<ReviewResponseDTO> getByRating(@PathVariable Integer rating) {
        return reviewService.getByRating(rating);
    }

    
    @GetMapping("/search")
    public List<ReviewResponseDTO> searchByComment(@RequestParam String keyword) {
        return reviewService.searchByComment(keyword);
    }

   
    @GetMapping("/latest")
    public List<ReviewResponseDTO> getLatestReviews() {
        return reviewService.getLatestReviews();
    }

    
    @GetMapping("/average")
    public Double getAverageRating() {
        return reviewService.getAverageRating();
    }
}
