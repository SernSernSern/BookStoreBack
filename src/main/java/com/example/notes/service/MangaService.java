package com.example.notes.service;

import com.example.notes.model.MangaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MangaService {
    MangaInfo findOne(String productId);

    Page<MangaInfo> findUpAll(Pageable pageable);

    Page<MangaInfo> findAll(Pageable pageable);

    Page<MangaInfo> findAllInCategory(Integer categoryType, Pageable pageable);

    void increaseStock(String productId, int amount);

    void decreaseStock(String productId, int amount);

    MangaInfo offSale(String productId);

    MangaInfo onSale(String productId);

    MangaInfo update(MangaInfo productInfo);
    MangaInfo save(MangaInfo productInfo);

    void delete(String productId);

}
