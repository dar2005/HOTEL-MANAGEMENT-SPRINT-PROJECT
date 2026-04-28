package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	List<Review> findByReservationReservationId(Long reservationId);
	
	 List<Review> findByRating(Integer rating);
	 
	 List<Review> findAllByOrderByReviewDateDesc();
	 
	 List<Review> findByRatingGreaterThan(Integer rating);
	 
	 List<Review> findByRatingLessThan(Integer rating);

	 List<Review> findByCommentContaining(String keyword);
	




}
