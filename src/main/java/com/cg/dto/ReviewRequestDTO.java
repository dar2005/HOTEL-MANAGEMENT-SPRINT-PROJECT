package com.cg.dto;

public class ReviewRequestDTO {
	 private Integer rating;
	 private String comment;
	 private Long reservationId;
	 
	 public ReviewRequestDTO(Integer rating, String comment, Long reservationId) {
	        this.rating = rating;
	        this.comment = comment;
	        this.reservationId = reservationId;
	    }
	 public ReviewRequestDTO() {}

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

	    public Long getReservationId() {
	        return reservationId;
	    }

	    public void setReservationId(Long reservationId) {
	        this.reservationId = reservationId;
	    }
}
