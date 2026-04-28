package com.cg.entity;
<<<<<<< HEAD

=======
>>>>>>> main
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private Long reservationId;

    private String guestName;
    private String guestEmail;
    private String guestPhone;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payment = new ArrayList<>();

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> review = new ArrayList<>();

    public Reservation() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
}