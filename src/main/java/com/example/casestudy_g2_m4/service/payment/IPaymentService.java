package com.example.casestudy_g2_m4.service.payment;

import com.example.casestudy_g2_m4.model.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IPaymentService {
   Map<String,Double> getRevenueByMonth();
   List<Payment> findAll();
}
