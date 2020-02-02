package com.mfun.controller;

import com.mfun.pojo.Book;
import com.mfun.pojo.Page;
import com.mfun.service.book.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 由于要在父类的方法内访问子类的私有方法，必须可访问私有方法
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误：没有该请求方法");
        }
    }

    static void page(HttpServletRequest req, HttpServletResponse resp,
                     BookService bookService, String path) {
        int pageNo = 1;
        String pn = req.getParameter("pn");
        try {
            pageNo = Integer.parseInt(pn);
        } catch (NumberFormatException e) {
//            System.out.println("页码参数不存在或格式错误");
        }
        try {
            Page<Book> page = bookService.getPage(pageNo);
            req.setAttribute("page", page);
            req.getRequestDispatcher(path).forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
