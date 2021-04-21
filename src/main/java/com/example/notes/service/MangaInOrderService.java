package com.example.notes.service;

import com.example.notes.model.MangaInOrder;
import com.example.notes.model.User;

public interface MangaInOrderService {
    void update(String itemId, Integer quantity, User user);
    MangaInOrder findOne(String itemId, User user);
}
