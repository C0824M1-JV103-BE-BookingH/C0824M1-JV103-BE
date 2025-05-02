package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {
}
