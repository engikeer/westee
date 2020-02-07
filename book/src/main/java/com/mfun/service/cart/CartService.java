package com.mfun.service.cart;

import com.mfun.pojo.Book;
import com.mfun.pojo.Cart;


public interface CartService {
    void addBook2Cart(Cart cart, Book book);
    void deleteItem(Cart cart, int bookId);
    void updateCount(Cart cart, int bookId, int count);
    void clearCart(Cart cart);
}
