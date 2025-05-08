package com.example.casestudy_g2_m4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.casestudy_g2_m4.model.BookingInfo;

public interface IBookingInfoRepository extends JpaRepository<BookingInfo, Integer> {
} 