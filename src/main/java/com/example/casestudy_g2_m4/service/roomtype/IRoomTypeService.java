package com.example.casestudy_g2_m4.service.roomtype;

import com.example.casestudy_g2_m4.model.RoomType;

import java.util.Optional;

public interface IRoomTypeService {
    RoomType save(RoomType roomType);
    Optional<RoomType> findByName(String name);
}
