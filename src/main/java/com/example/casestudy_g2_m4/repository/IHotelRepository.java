package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel,Long> {
    Hotel findByOwner(String owner);
}
