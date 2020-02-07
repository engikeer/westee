package com.mfun.service;

import com.mfun.dao.book.BookDao;
import com.mfun.dao.book.BookDaoImpl;
import com.mfun.pojo.Book;
import com.mfun.pojo.Cart;
import com.mfun.pojo.CartItem;
import com.mfun.pojo.User;
import com.mfun.service.order.OrderService;
import com.mfun.service.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @Test
    public void checkoutTest() throws SQLException {
        OrderService orderService = new OrderServiceImpl();
        BookDao bookDao = new BookDaoImpl();
        // 构建 Cart
        Book book1 = bookDao.getBookById(new Book(1));
        Book book2 = bookDao.getBookById(new Book(2));
        CartItem item1 = new CartItem(book1, 3);
        CartItem item2 = new CartItem(book2, 50);
        Cart cart = new Cart();
        cart.addCartItem(item1);
        cart.addCartItem(item2);
        orderService.checkout(cart, new User(1));
        assertEquals(book1.getSales() + 3, bookDao.getBookById(new Book(1)).getSales());
        assertEquals(book1.getStock() - 3, bookDao.getBookById(new Book(1)).getStock());
        assertEquals(book2.getSales() + 50, bookDao.getBookById(new Book(2)).getSales());
        assertEquals(book2.getStock() - 50, bookDao.getBookById(new Book(2)).getStock());

    }
}
