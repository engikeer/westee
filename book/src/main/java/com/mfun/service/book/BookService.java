package com.mfun.service.book;

import com.mfun.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<Book> getAll() throws SQLException;
    Book get(Book book) throws SQLException;
    boolean add(Book book);
    boolean update(Book book);
    boolean delete(Book book);
}
