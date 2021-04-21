package com.example.notes.service.impl;

import com.example.notes.enums.MangaStatusEnum;
import com.example.notes.enums.ResultEnum;
import com.example.notes.exception.MyException;
import com.example.notes.model.MangaInfo;
import com.example.notes.repository.MangaInOrderRepo;
import com.example.notes.repository.MangaInfoRepo;
import com.example.notes.service.CategoryService;
import com.example.notes.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class MangaServiceImpl implements MangaService {

    @Autowired
    MangaInfoRepo mangaInfoRepo;

    @Autowired
    CategoryService categoryService;

    @Override
    public MangaInfo findOne(String productId) {
        MangaInfo mangaInfo = mangaInfoRepo.findByProductId(productId);
        return mangaInfo;
    }

    @Override
    public Page<MangaInfo> findUpAll(Pageable pageable) {
        return mangaInfoRepo.findAllByProductStatusOrderByProductIdAsc(MangaStatusEnum.UP.getCode(), pageable);
    }

    @Override
    public Page<MangaInfo> findAll(Pageable pageable) {
        return mangaInfoRepo.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<MangaInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return mangaInfoRepo.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        MangaInfo mangaInfo = findOne(productId);
        if(mangaInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = mangaInfo.getProductStock() + amount;
        mangaInfo.setProductStock(update);
        mangaInfoRepo.save(mangaInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        MangaInfo mangaInfo = findOne(productId);
        if (mangaInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = mangaInfo.getProductStock() - amount;
        if(update <= 0) throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH );

        mangaInfo.setProductStock(update);
        mangaInfoRepo.save(mangaInfo);
    }

    @Override
    @Transactional
    public MangaInfo offSale(String productId) {
        MangaInfo mangaInfo = findOne(productId);
        if (mangaInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (mangaInfo.getProductStatus() == MangaStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        mangaInfo.setProductStatus(MangaStatusEnum.DOWN.getCode());
        return mangaInfoRepo.save(mangaInfo);
    }

    @Override
    @Transactional
    public MangaInfo onSale(String productId) {
        MangaInfo mangaInfo = findOne(productId);
        if (mangaInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (mangaInfo.getProductStatus() == MangaStatusEnum.UP.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        mangaInfo.setProductStatus(MangaStatusEnum.UP.getCode());
        return mangaInfoRepo.save(mangaInfo);
    }

    @Override
    public MangaInfo update(MangaInfo productInfo) {
        categoryService.findByCategoryType(productInfo.getCategoryType());
        if(productInfo.getProductStatus() > 1) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }


        return mangaInfoRepo.save(productInfo);
    }

    @Override
    public MangaInfo save(MangaInfo productInfo) {
        return update(productInfo);
    }

    @Override
    public void delete(String productId) {
        MangaInfo mangaInfo = findOne(productId);
        if (mangaInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        mangaInfoRepo.delete(mangaInfo);
    }
}
