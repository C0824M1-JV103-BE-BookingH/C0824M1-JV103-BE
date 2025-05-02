package com.example.casestudy_g2_m4.service.roomtype;

import com.example.casestudy_g2_m4.model.RoomType;
import com.example.casestudy_g2_m4.repository.IRoomtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoomTypeService implements IRoomTypeService {
    @Autowired
    private IRoomtypeRepository roomtypeRepository;
    @Override
    public RoomType save(RoomType roomType) {
        return roomtypeRepository.save(roomType);
    }

    @Override
    public Optional<RoomType> findByName(String name) {
        return roomtypeRepository.findRoomTypeByName(name);
    }
}
