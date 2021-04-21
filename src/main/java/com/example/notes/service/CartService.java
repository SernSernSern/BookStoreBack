package com.example.notes.service;

import com.example.notes.model.Cart;
import com.example.notes.model.MangaInOrder;
import com.example.notes.model.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<MangaInOrder> mangaInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
