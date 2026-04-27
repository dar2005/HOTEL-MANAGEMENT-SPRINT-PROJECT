package com.cg.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    
    public Review() {}

    public Review(int rating, String comment, LocalDate reviewDate, Reservation reservation) {
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reservation = reservation;
    }

    

    public Long getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}