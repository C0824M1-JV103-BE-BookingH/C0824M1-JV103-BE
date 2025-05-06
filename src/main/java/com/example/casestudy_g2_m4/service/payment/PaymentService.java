package com.example.casestudy_g2_m4.service.payment;

import com.example.casestudy_g2_m4.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    private IPaymentRepository paymentRepository;
    @Override
    public Map<String, Double> getRevenueByMonth() {
        List<Object[]> results = paymentRepository.findRevenueByMonth();
        System.out.println("Kết quả truy vấn: " + results);
        Map<String, Double> revenueData = new TreeMap<>();
        for (Object[] result : results) {
            String month = (String) result[0];
            Number revenue = (Number) result[1];
            revenueData.put(month, revenue.doubleValue());
        }
        System.out.println("Dữ liệu doanh thu: " + revenueData);
        return revenueData;
    }
}
