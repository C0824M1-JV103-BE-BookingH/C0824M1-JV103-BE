package com.example.casestudy_g2_m4.service.bookinginfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.casestudy_g2_m4.model.BookingInfo;
import com.example.casestudy_g2_m4.repository.IBookingInfoRepository;

@Service
public class BookingInfoService implements IBookingInfoService {
    @Autowired
    private IBookingInfoRepository bookingInfoRepository;

    @Override
    public BookingInfo save(BookingInfo bookingInfo) {
        return bookingInfoRepository.save(bookingInfo);
    }
} 