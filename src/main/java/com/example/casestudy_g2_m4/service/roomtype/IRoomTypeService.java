package com.example.casestudy_g2_m4.service.roomtype;

import com.example.casestudy_g2_m4.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRoomTypeService {
    List<RoomType> findAllRoomType();
    Page<RoomType> findAll(Pageable pageable);
    RoomType save(RoomType roomType);
    Optional<RoomType> findByName(String name);
    Optional<RoomType> findById(Integer id);

}
