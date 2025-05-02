package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoomtypeRepository extends JpaRepository<RoomType,Long> {
    Optional<RoomType> findRoomTypeByName(String name);
}
