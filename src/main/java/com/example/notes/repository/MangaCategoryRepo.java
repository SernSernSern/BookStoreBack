package com.example.notes.repository;

import com.example.notes.model.MangaCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaCategoryRepo extends JpaRepository<MangaCategory, Long> {
    List<MangaCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    List<MangaCategory> findAllByOrderByCategoryType();
    MangaCategory findByCategoryType(Integer categoryType);
}
