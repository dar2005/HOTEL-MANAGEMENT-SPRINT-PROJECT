package com.cg.dto;

import java.time.LocalDate;

public class ReviewResponseDTO {
	  	private Long reviewId;
	    private Integer rating;
	    private String comment;
	    private LocalDate reviewDate;

	    public ReviewResponseDTO() {
	    }

	    public ReviewResponseDTO(Long reviewId, Integer rating, String comment, LocalDate reviewDate) {
	        this.reviewId = reviewId;
	        this.rating = rating;
	        this.comment = comment;
	        this.reviewDate = reviewDate;
	    }

	    public Long getReviewId() {
	        return reviewId;
	    }

	    public void setReviewId(Long reviewId) {
	        this.reviewId = reviewId;
	    }

	    public Integer getRating() {
	        return rating;
	    }

	    public void setRating(Integer rating) {
	        this.rating = rating;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }

	    public LocalDate getReviewDate() {
	        return reviewDate;
	    }

	    public void setReviewDate(LocalDate reviewDate) {
	        this.reviewDate = reviewDate;
	    }
}
