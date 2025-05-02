package com.example.casestudy_g2_m4.service.room;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.model.RoomType;
import com.example.casestudy_g2_m4.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoomService implements IRoomService {
    private static final Random random = new Random();
    @Autowired
    private IRoomRepository roomRepository;


    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

}
