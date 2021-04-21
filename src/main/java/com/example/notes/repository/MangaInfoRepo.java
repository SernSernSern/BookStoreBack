package com.example.notes.repository;

import com.example.notes.model.MangaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaInfoRepo extends JpaRepository<MangaInfo, String> {

    MangaInfo findByProductId(String id);

    Page<MangaInfo> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    Page<MangaInfo> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<MangaInfo> findAllByOrderByProductId(Pageable pageable);

}
