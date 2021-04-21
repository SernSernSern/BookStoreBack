package com.example.notes.service.impl;

import com.example.notes.enums.ResultEnum;
import com.example.notes.exception.MyException;
import com.example.notes.model.MangaCategory;
import com.example.notes.repository.MangaCategoryRepo;
import com.example.notes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    MangaCategoryRepo mangaCategoryRepo;

    @Override
    public List<MangaCategory> findAll() {
        List<MangaCategory> res = mangaCategoryRepo.findAllByOrderByCategoryType();
        return res;
    }

    @Override
    public MangaCategory findByCategoryType(Integer categoryType) {
        MangaCategory res = mangaCategoryRepo.findByCategoryType(categoryType);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public List<MangaCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<MangaCategory> res = mangaCategoryRepo.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    @Transactional
    public MangaCategory save(MangaCategory productCategory) {
        return mangaCategoryRepo.save(productCategory);
    }
}
