package com.example.casestudy_g2_m4.service;

import com.example.casestudy_g2_m4.model.Hotel;
import com.example.casestudy_g2_m4.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelRepository iHotelRepository;

    @Override
    public List<Hotel> findAll() {
        return iHotelRepository.findAll();
    }
    @Override
    public Hotel findByOwner(String owner) {
        return iHotelRepository.findByOwner(owner);
    }

    @Override
    public void update(Hotel hotel) {
        Hotel existingHotel = iHotelRepository.findByOwner(hotel.getOwner());
        if (existingHotel != null) {
            existingHotel.setName(hotel.getName());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setHotline(hotel.getHotline());
            existingHotel.setImageUrl(hotel.getImageUrl());
            iHotelRepository.save(existingHotel);
        }
    }
}
