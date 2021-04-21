package com.example.notes.service.impl;

import com.example.notes.model.MangaInOrder;
import com.example.notes.model.User;
import com.example.notes.repository.MangaInOrderRepo;
import com.example.notes.service.MangaInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class MangaInOrderServiceImpl implements MangaInOrderService {
    @Autowired
    MangaInOrderRepo mangaInOrderRepo;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(mangaInOrder -> {
            mangaInOrder.setCount(quantity);
            mangaInOrderRepo.save(mangaInOrder);
        });

    }

    @Override
    public MangaInOrder findOne(String itemId, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<MangaInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
