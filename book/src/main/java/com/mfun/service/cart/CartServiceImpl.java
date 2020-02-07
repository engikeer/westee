package com.mfun.service.cart;

import com.mfun.pojo.Book;
import com.mfun.pojo.Cart;
import com.mfun.pojo.CartItem;

import java.util.Map;

public class CartServiceImpl implements CartService {
    @Override
    public void addBook2Cart(Cart cart, Book book) {
        Map<Integer, CartItem> items = cart.getItems();
        int id = book.getId();
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(cartItem.getCount() + 1);
        } else {
            items.put(id, new CartItem(book, 1));
        }
    }

    @Override
    public void deleteItem(Cart cart, int bookId) {
        cart.getItems().remove(bookId);

    }

    @Override
    public void updateCount(Cart cart, int bookId, int count) {
        CartItem item = cart.getItems().get(bookId);
        item.setCount(count);
    }

    @Override
    public void clearCart(Cart cart) {
        cart.getItems().clear();
    }
}
