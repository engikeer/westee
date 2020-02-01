package com.mfun.controller;

import com.mfun.pojo.Book;
import com.mfun.service.book.BookService;
import com.mfun.service.book.BookServiceImpl;
import com.mfun.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class BookManagerServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    private void list(HttpServletRequest req, HttpServletResponse resp) {

        try {
            List<Book> books = bookService.getAll();
            req.setAttribute("list", books);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Book book = ServletUtils.param2bean(req, Book.class);
            if (bookService.add(book)) {
                // 添加成功，回到列表页面（回添加页面并显示信息会更好）
                resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=list");
            } else {
                // 添加不成功，返回添加页面并给出提示（由于当前没有约束，不会发生）
                System.out.println("保存不成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Book book = ServletUtils.param2bean(req, Book.class);
            bookService.delete(book);
            resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=list");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
