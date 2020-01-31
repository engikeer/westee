package com.mfun.dao;

import com.mfun.dao.book.BookDao;
import com.mfun.dao.book.BookDaoImpl;
import com.mfun.pojo.Book;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookDaoTest {
    @Test
    public void getBookTest() throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.getAllBooks();
        assertEquals("程心", books.get(0).getAuthor());
        Book book = new Book();
        book.setId(1);
        assertEquals("程心", bookDao.getBookById(book).getAuthor());
        System.out.println(bookDao.getBookById(book));
    }

    @Test
    public void cudBookTest() throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        // 新增
        Book book = new Book(null, "放弃", "托马斯·维德",
                12.1, 1, 499, null);
        bookDao.addBook(book);
        List<Book> books = bookDao.getBooksByTitle(book);
        assertEquals(12.1, books.get(books.size() - 1).getPrice());
        // 修改
        book.setId(books.get(books.size() - 1).getId());
        book.setPrice(0.1);
        bookDao.updateBook(book);
        books = bookDao.getBooksByTitle(book);
        assertEquals(0.1, books.get(books.size() - 1).getPrice());
        // 删除
        bookDao.deleteBook(book);
    }
}
