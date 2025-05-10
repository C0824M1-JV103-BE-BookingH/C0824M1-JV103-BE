package com.example.casestudy_g2_m4.service.roomtype;

import com.example.casestudy_g2_m4.model.RoomType;
import com.example.casestudy_g2_m4.repository.IRoomtypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoomTypeService implements IRoomTypeService {
    @Autowired
    private IRoomtypeRepository roomTypeRepository;

    @Override
    public List<RoomType> findAllRoomType() {
        return roomTypeRepository.findAll();
    }

    @Override
    public Page<RoomType> findAll(Pageable pageable) {
        return roomTypeRepository.findAll(pageable);
    }

    @Override
    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Override
    public Optional<RoomType> findByName(String name) {
        return roomTypeRepository.findRoomTypeByName(name);
    }

    @Override
    public Optional<RoomType> findById(Integer id) {
        return roomTypeRepository.findById(id);
    }

}
