package com.example.casestudy_g2_m4.service.hotel;

import com.example.casestudy_g2_m4.model.Hotel;

import java.util.List;

public interface IHotelService {
    List<Hotel> findAll();
    Hotel findByOwner(String owner);
    void update(Hotel hotel);
}
