package com.mfun.controller;

import com.mfun.pojo.Book;
import com.mfun.pojo.Page;
import com.mfun.service.book.BookService;
import com.mfun.service.book.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BookUserServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    private void page(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("url", "user/bookUser?action=page");
        BaseServlet.page(req, resp, bookService, "/pages/client/index.jsp");
    }

    private void pageByPrice(HttpServletRequest req, HttpServletResponse resp) {
        double min = 0.0;
        double max = Double.MAX_VALUE;
        String reqMin = req.getParameter("min");
        String reqMax = req.getParameter("max");
        try {
            min = Double.parseDouble(reqMin);
        } catch (NumberFormatException e) {
//            System.out.println("min 格式错误");
        }
        try {
            max = Double.parseDouble(reqMax);
        } catch (NumberFormatException e) {
//            System.out.println("max 格式错误");
        }
        int pageNo = 1;
        String pn = req.getParameter("pn");
        try {
            pageNo = Integer.parseInt(pn);
        } catch (NumberFormatException e) {
//            System.out.println("pageNo 格式错误");
        }
        try {
            Page<Book> page = bookService.getPageByPrice(pageNo, min, max);
            req.setAttribute("page", page);
            String url = (String) req.getAttribute("url");
            req.setAttribute("url", "user/bookUser?action=pageByPrice&min=" + min + "&max=" + max);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }



    }
}
