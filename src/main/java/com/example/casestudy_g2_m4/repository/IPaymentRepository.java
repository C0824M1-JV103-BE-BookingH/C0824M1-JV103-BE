package com.example.casestudy_g2_m4.repository;

import java.util.List;

import com.example.casestudy_g2_m4.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.casestudy_g2_m4.model.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
//    @Query("SELECT function('DATE', p.paidAt) as date, SUM(p.amount + COALESCE(p.surcharge, 0)) as Total " +
//            "FROM Payment p JOIN p.booking b " +
//            "WHERE p.paidAt BETWEEN :startDate AND :endDate " +
//            "AND b.paymentStatus = 'paid' " +
//            "GROUP BY function('DATE', p.paidAt)")
//    List<Object[]> getRevenueByDay(@Param("startDate") LocalDateTime startDate,
//                                   @Param("endDate") LocalDateTime endDate);

    @Query("SELECT p FROM Payment p WHERE p.booking = :booking")
    List<Payment> findByBooking(Booking booking);

    @Query("SELECT FUNCTION('DATE_FORMAT', p.paidAt, '%Y-%m') as month, SUM(p.amount + COALESCE(p.surcharge, 0)) as revenue " +
            "FROM Payment p " +
            "GROUP BY FUNCTION('DATE_FORMAT', p.paidAt, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', p.paidAt, '%Y-%m')")
    List<Object[]> findRevenueByMonth();
    @Modifying
    @Query("DELETE FROM Payment p WHERE p.booking.id = :bookingId")
    void deleteByBookingId(Integer bookingId);
}
