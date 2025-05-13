package com.example.casestudy_g2_m4.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class BookingDTO {
    @NotNull(message = "Ngày check-in không được để trống")
    @Future(message = "Ngày check-in phải là ngày trong tương lai")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkIn;

    @NotNull(message = "Ngày check-out không được để trống")
    @Future(message = "Ngày check-out phải là ngày trong tương lai")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkOut;

    @AssertTrue(message = "Ngày check-out phải sau ngày check-in ít nhất 1 ngày")
    public boolean isValidCheckOutDate() {
        if (checkIn == null || checkOut == null) {
            return true; // Let @NotNull handle this case
        }
        return checkOut.isAfter(checkIn.plusHours(24));
    }

    @AssertTrue(message = "Ngày check-in phải trước ngày check-out")
    public boolean isCheckInBeforeCheckOut() {
        if (checkIn == null || checkOut == null) {
            return true; // Let @NotNull handle this case
        }
        return checkIn.isBefore(checkOut);
    }
} 