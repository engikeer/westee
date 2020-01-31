package com.mfun.dao.book;

import com.mfun.dao.BaseDaoImpl;
import com.mfun.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

    public BookDaoImpl() {
        super(Book.class);
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        // 由于 imgPath 属性与数据库字段名不匹配，通过字段别名进行匹配
        String sql = "SELECT *, img_path as imgPath FROM bs_book";
        return getBeanList(sql);
    }

    @Override
    public List<Book> getBooksByTitle(Book book) throws SQLException {
        String sql = "SELECT *, img_path as imgPath FROM bs_book WHERE title LIKE ?";
        return getBeanList(sql, "%" + book.getTitle() + "%");
    }

    @Override
    public Book getBookById(Book book) throws SQLException {
        String sql = "SELECT *, img_path as imgPath FROM bs_book WHERE id=?";
        return getBean(sql, book.getId());
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "INSERT INTO bs_book VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        try {
            int rowCount = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(),
                    book.getSales(), book.getStock(), book.getImgPath());
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(Book book) {
        String sql = "DELETE FROM bs_book WHERE id = ?";
        try {
            int rowCount = update(sql, book.getId());
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "UPDATE bs_book SET title=?, author=?, " +
                "price=?, sales=?, stock=?, img_path=? WHERE id=?";
        try {
            int rowCount = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(),
                    book.getSales(), book.getStock(), book.getImgPath(), book.getId());
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
