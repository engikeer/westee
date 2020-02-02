package com.mfun.controller;

import com.mfun.pojo.Book;
import com.mfun.pojo.Page;
import com.mfun.service.book.BookService;
import com.mfun.service.book.BookServiceImpl;
import com.mfun.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

    private void page(HttpServletRequest req, HttpServletResponse resp) {
        int pageNo = 1;
        String pn = req.getParameter("pn");
        try {
            pageNo = Integer.parseInt(pn);
        } catch (NumberFormatException e) {
            System.out.println("页码参数不存在或格式错误");
        }
        try {
            Page<Book> page = bookService.getPage(pageNo);
            req.setAttribute("page", page);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

//    private void add(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            Book book = ServletUtils.param2bean(req, Book.class);
//            if (bookService.add(book)) {
//                // 添加成功，回到列表页面（回添加页面并显示信息会更好）
//                resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=page");
//            } else {
//                // 添加不成功，返回添加页面并给出提示（由于当前没有约束，不会发生）
//                System.out.println("保存不成功");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
//        String pn = req.getParameter("pn");
        String referer = req.getHeader("Referer");
        try {
            Book book = ServletUtils.param2bean(req, Book.class);
            bookService.delete(book);
//            resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=page&pn=" + pn);
            resp.sendRedirect(referer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getBook(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Book requestBook = ServletUtils.param2bean(req, Book.class);
            Book book = bookService.get(requestBook);
            // 跳转到编辑页面显示图书的信息
            req.setAttribute("book", book);
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Book book = ServletUtils.param2bean(req, Book.class);
            // 添加时没有 id 值，beanUtils 赋予的默认为 0；如果 id 不为 0 就是修改已有图书
            if (book.getId() > 0) {
                String pn = req.getParameter("pn");
                bookService.update(book);
                // 回到原来的页面
                resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=page&pn=" + pn);
            } else {
                bookService.add(book);
                Page<Book> page = bookService.getPage(1);
                // 回到最后一页
                resp.sendRedirect(req.getContextPath() + "/manager/bookManager?action=page&pn=" + page.getPageCount());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
