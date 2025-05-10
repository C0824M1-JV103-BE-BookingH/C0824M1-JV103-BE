package com.example.casestudy_g2_m4.service.room;

import com.example.casestudy_g2_m4.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRoomService {
 Page<Room> findAllRoom(Pageable pageable);
 Room save(Room room);
 boolean existsByRoomNumber(String roomNumber);
 void deleteById(Integer id);
 Optional<Room> findById(Integer id);
 Room findByRoomNumber(String roomNumber);
}