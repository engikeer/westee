package com.mfun.service.book;

import com.mfun.dao.book.BookDao;
import com.mfun.dao.book.BookDaoImpl;
import com.mfun.pojo.Book;
import com.mfun.pojo.Page;

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
    public Page<Book> getPage(int pageNo) throws SQLException {
        Page<Book> page = new Page<>(bookDao.getTotalCount(), pageNo);
        List<Book> books = bookDao.getPageList(page.getIndex(), page.getPageSize());
        page.setData(books);
        return page;
    }

    @Override
    public Page<Book> getPageByPrice(int pageNo, double min, double max) throws SQLException {
        Page<Book> page = new Page<>(bookDao.getCountByPrice(min, max), pageNo);
        List<Book> books = bookDao.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);
        page.setData(books);
        return page;
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
