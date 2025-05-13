package com.example.casestudy_g2_m4.service.Gallary;

import com.example.casestudy_g2_m4.model.ResortImage;

import java.util.List;

public interface IGalleryService {
  List<ResortImage> findByCategory(String category);
}
