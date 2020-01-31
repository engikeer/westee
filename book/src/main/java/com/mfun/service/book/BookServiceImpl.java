package com.mfun.service.book;

import com.mfun.dao.book.BookDao;
import com.mfun.dao.book.BookDaoImpl;
import com.mfun.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAll() throws SQLException {
        return bookDao.getAllBooks();
    }

    @Override
    public Book get(Book book) throws SQLException {
        return bookDao.getBookById(book);
    }

    @Override
    public boolean add(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public boolean update(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public boolean delete(Book book) {
        return bookDao.deleteBook(book);
    }
}
