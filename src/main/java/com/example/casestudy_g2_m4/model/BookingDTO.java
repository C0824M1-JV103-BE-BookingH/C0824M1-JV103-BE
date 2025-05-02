package com.example.casestudy_g2_m4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingDTO {
    private Integer id;
    private Integer userId;
    @NotBlank(message = "User name is required")
    private String userName;
    @NotBlank(message = "Room type is required")
    private String roomType;
    @NotNull(message = "Check-in date and time is required")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime checkIn;
    @NotNull(message = "Check-out date and time is required")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime checkOut;
    private String createdAt;
    private String status;
    private String paymentStatus;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Constructor để ánh xạ từ Booking
    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.userId = booking.getUser() != null ? booking.getUser().getId() : null;
        this.userName = booking.getUser() != null ? booking.getUser().getName() : "";
        this.roomType = booking.getRoom() != null && booking.getRoom().getRoomType() != null
                ? booking.getRoom().getRoomType().getName() : "";
        this.checkIn = booking.getCheckIn();
        this.checkOut = booking.getCheckOut();
        this.createdAt = booking.getCreatedAt() != null ? booking.getCreatedAt().format(FORMATTER) : "";
        this.status = booking.getStatus() != null ? booking.getStatus().toString() : "";
        this.paymentStatus = booking.getPaymentStatus() != null ? booking.getPaymentStatus().toString() : "";
    }

    public BookingDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}