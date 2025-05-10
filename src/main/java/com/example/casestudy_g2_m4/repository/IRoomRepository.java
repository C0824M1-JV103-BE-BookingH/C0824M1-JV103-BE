package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {
    @Override
    Page<Room> findAll(Pageable pageable);
    boolean existsByRoomNumber(String roomNumber);
    Room findByRoomNumber(String roomNumber);
}
