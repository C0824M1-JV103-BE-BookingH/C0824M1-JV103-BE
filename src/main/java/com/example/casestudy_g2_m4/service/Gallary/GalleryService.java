package com.example.casestudy_g2_m4.service.Gallary;

import com.example.casestudy_g2_m4.model.ResortImage;
import com.example.casestudy_g2_m4.repository.IGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GalleryService implements IGalleryService{
    @Autowired
    private IGalleryRepository galleryRepository;
    @Override
    public List<ResortImage> findByCategory(String category) {
        return galleryRepository.findByCategory(category);
    }
}
