package com.example.casestudy_g2_m4.service.room;

import com.example.casestudy_g2_m4.model.Room;
import com.example.casestudy_g2_m4.model.RoomType;

import java.util.List;

public interface IRoomService {
 List<Room> findAllRoom();
 Room save(Room room);
}