package com.mfun.service.book;

import com.mfun.pojo.Book;
import com.mfun.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<Book> getAll() throws SQLException;
    Book get(Book book) throws SQLException;
    Page<Book> getPage(int pageNo) throws SQLException;
    Page<Book> getPageByPrice(int pageNo, double min, double max) throws SQLException;
    boolean add(Book book);
    boolean update(Book book);
    boolean delete(Book book);
}
