package com.mfun.dao.book;

import com.mfun.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    /**
     * 获取所有图书
     * @return 所有图书的列表
     * @throws SQLException 查询异常
     */
    List<Book> getAllBooks() throws SQLException;

    List<Book> getBooksByTitle(Book book) throws SQLException;

    Book getBookById(Book book) throws SQLException;

    List<Book> getPageList(int index, int size) throws SQLException;

    int getTotalCount() throws SQLException;

    boolean addBook(Book book);

    boolean deleteBook(Book book);

    boolean updateBook(Book book);

}
