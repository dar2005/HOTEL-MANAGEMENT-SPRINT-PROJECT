package com.cg.dto;

import java.time.LocalDate;

public class ReviewResponseDTO {

    private Long reviewId;
    private Long reservationId;
    private String reviewerName;
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

    public ReviewResponseDTO(Long reviewId, Long reservationId, Integer rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reservationId = reservationId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public ReviewResponseDTO(Long reviewId, Long reservationId, String reviewerName, Integer rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reservationId = reservationId;
        this.reviewerName = reviewerName;
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

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
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
