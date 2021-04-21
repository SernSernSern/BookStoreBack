package com.example.notes.controller;

import com.example.notes.form.ItemForm;
import com.example.notes.model.Cart;
import com.example.notes.model.MangaInOrder;
import com.example.notes.model.User;
import com.example.notes.repository.MangaInOrderRepo;
import com.example.notes.service.CartService;
import com.example.notes.service.MangaInOrderService;
import com.example.notes.service.MangaService;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    MangaService mangaService;
    @Autowired
    MangaInOrderService mangaInOrderService;
    @Autowired
    MangaInOrderRepo mangaInOrderRepo;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<MangaInOrder> mangaInOrders, Principal principal) {
        User user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(mangaInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return cartService.getCart(user);
    }


    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = mangaService.findOne(form.getProductId());
        try {
            mergeCart(Collections.singleton(new MangaInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public MangaInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findOne(principal.getName());
        mangaInOrderService.update(itemId, quantity, user);
        return mangaInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
        cartService.delete(itemId, user);
        // flush memory into DB
    }


    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        User user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }

}
