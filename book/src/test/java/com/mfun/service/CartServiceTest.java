package com.mfun.service;

import com.mfun.pojo.Book;
import com.mfun.pojo.Cart;
import com.mfun.pojo.CartItem;
import com.mfun.service.cart.CartService;
import com.mfun.service.cart.CartServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class CartServiceTest {

    @Test
    public void addTest() {
        CartService cartService = new CartServiceImpl();
        Book book1 = new Book(13, "资治通鉴", "司马光", 635.65, 2,12, null);
        Book book2 = new Book(14, "中华民国史", "李新", 782.12, 1,13, null);
        Cart cart = new Cart();
        cartService.addBook2Cart(cart, book1);
        cartService.addBook2Cart(cart, book2);
        cartService.addBook2Cart(cart, book1);
        assertEquals(2053.42, cart.getTotalPrice());
        cartService.updateCount(cart, 14, 3);
        assertEquals(3617.66, cart.getTotalPrice());
        cartService.deleteItem(cart, book1.getId());
        assertEquals(2346.36, cart.getTotalPrice());
        cartService.clearCart(cart);
        assertEquals(0, cart.getTotalPrice());

    }
}
