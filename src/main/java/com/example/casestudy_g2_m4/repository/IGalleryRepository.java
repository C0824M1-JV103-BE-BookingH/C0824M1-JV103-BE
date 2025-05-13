package com.example.casestudy_g2_m4.repository;

import com.example.casestudy_g2_m4.model.ResortImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGalleryRepository extends JpaRepository<ResortImage,Integer> {
    List<ResortImage> findByCategory(String category);
}
