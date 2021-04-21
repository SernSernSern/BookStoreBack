package com.example.notes.service;

import com.example.notes.model.MangaCategory;

import java.util.List;

public interface CategoryService {
    List<MangaCategory> findAll();

    MangaCategory findByCategoryType(Integer categoryType);

    List<MangaCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    MangaCategory save(MangaCategory productCategory);
}
