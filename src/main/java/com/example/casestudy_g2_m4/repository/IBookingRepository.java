package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Booking;
import com.example.casestudy_g2_m4.model.BookingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b " +
            "JOIN b.room r " +
            "WHERE (b.bookingInfo.name IS NOT NULL AND LOWER(b.bookingInfo.name) LIKE LOWER(CONCAT('%', :keyword, '%')) )" +
            "OR LOWER(r.roomType.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.status) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.paymentStatus) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "AND (:checkIn IS NULL OR b.checkIn >= :checkIn) " +
            "AND (:checkOut IS NULL OR b.checkOut <= :checkOut) " +
            "AND (:createdAt IS NULL OR b.createdAt = :createdAt)")
    List<Booking> searchByKeyword(@Param("keyword") String keyword,
                                  @Param("checkIn") LocalDateTime checkIn,
                                  @Param("checkOut") LocalDateTime checkOut,
                                  @Param("createdAt") LocalDateTime createdAt);

}
