package com.example.casestudy_g2_m4.service.room;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class RoomService implements IRoomService {
    private static final Random random = new Random();
    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public Page<Room> findAllRoom(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public boolean existsByRoomNumber(String roomNumber) {
        return roomRepository.existsByRoomNumber(roomNumber);
    }

    @Override
    public void deleteById(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Optional<Room> findById(Integer id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room findByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

}
