package com.example.casestudy_g2_m4.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Method method;

    @Column(nullable = false)
    private Double amount;

    private Double surcharge;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    public enum Method {
        cash, bank
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public Method getMethod() { return method; }
    public void setMethod(Method method) { this.method = method; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Double getSurcharge() { return surcharge; }
    public void setSurcharge(Double surcharge) { this.surcharge = surcharge; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
