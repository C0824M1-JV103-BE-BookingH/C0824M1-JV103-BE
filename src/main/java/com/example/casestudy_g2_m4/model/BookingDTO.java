package com.example.casestudy_g2_m4.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {
    private Integer id;
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
    private String email;
    private String paymentMethod;
    private Double price;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Constructor để ánh xạ từ Booking
    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.userName = booking.getBookingInfo() != null ? booking.getBookingInfo().getName() : "";
        this.email = booking.getBookingInfo() != null ? booking.getBookingInfo().getEmail() : "";
        this.roomType = booking.getRoom() != null && booking.getRoom().getRoomType() != null
                ? booking.getRoom().getRoomType().getName() : "";
        this.checkIn = booking.getCheckIn();
        this.checkOut = booking.getCheckOut();
        this.createdAt = booking.getCreatedAt() != null ? booking.getCreatedAt().format(FORMATTER) : "";
        this.status = booking.getStatus() != null ? booking.getStatus().toString() : "";
        this.paymentStatus = booking.getPaymentStatus() != null ? booking.getPaymentStatus().toString() : "";
    }

    public BookingDTO(String userName, String email, String roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.userName = userName;
        this.email = email;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public BookingDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}