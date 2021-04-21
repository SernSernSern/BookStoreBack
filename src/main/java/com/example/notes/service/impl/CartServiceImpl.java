package com.example.notes.service.impl;

import com.example.notes.model.Cart;
import com.example.notes.model.MangaInOrder;
import com.example.notes.model.OrderMain;
import com.example.notes.model.User;
import com.example.notes.repository.CartRepo;
import com.example.notes.repository.MangaInOrderRepo;
import com.example.notes.repository.OrderRepo;
import com.example.notes.repository.UserRepo;
import com.example.notes.service.CartService;
import com.example.notes.service.MangaService;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    MangaService mangaService;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    MangaInOrderRepo mangaInOrderRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<MangaInOrder> mangaInOrders, User user) {
            Cart finalCart = user.getCart();
            mangaInOrders.forEach(mangaInOrder->{
                Set<MangaInOrder> set = finalCart.getProducts();
                Optional<MangaInOrder> old = set.stream().filter(e -> e.getProductId().equals(mangaInOrder.getProductId())).findFirst();
                MangaInOrder manga;
                if(old.isPresent()){
                    manga = old.get();
                    manga.setCount(mangaInOrder.getCount() + manga.getCount());
                }else{
                    manga = mangaInOrder;
                    manga.setCart(finalCart);
                    finalCart.getProducts().add(manga);
                }
                mangaInOrderRepo.save(manga);
            });
            cartRepo.save(finalCart);
    }

    @Override
    @Transactional
    public void delete(String itemId, User user) {
        var op = user.getCart().getProducts().stream().filter(e->itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(mangaInOrder -> {
            mangaInOrder.setCart(null);
            mangaInOrderRepo.deleteById(mangaInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {
        OrderMain orderMain = new OrderMain(user);
        orderRepo.save(orderMain);

        user.getCart().getProducts().forEach(mangaInOrder -> {
            mangaInOrder.setCart(null);
            mangaInOrder.setOrderMain(orderMain);
            mangaService.decreaseStock(mangaInOrder.getProductId(), mangaInOrder.getCount());
            mangaInOrderRepo.save(mangaInOrder);
        });
    }
}
