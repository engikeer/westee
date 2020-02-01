package com.mfun.service;

import com.mfun.pojo.Book;
import com.mfun.pojo.Page;
import com.mfun.service.book.BookService;
import com.mfun.service.book.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class BookServiceTest {
    @Test
    public void getPageTest() throws SQLException {
        BookService bookService = new BookServiceImpl();
        Page<Book> bookPage = bookService.getPage(6);
        System.out.println(bookPage);
        for (Book book : bookPage.getData()) {
            System.out.println(book);
        }
    }
}
