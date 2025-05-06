package com.example.casestudy_g2_m4.service.room;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomService {
 Page<Room> findAllRoom(Pageable pageable);
 Room save(Room room);
}