package com.example.casestudy_g2_m4.service.bookinginfo;

import com.example.casestudy_g2_m4.model.BookingInfo;

public interface IBookingInfoService {
    BookingInfo save(BookingInfo bookingInfo);
    void delete(Integer id);
} 