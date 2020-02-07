package com.mfun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfun.pojo.Book;
import com.mfun.pojo.Cart;
import com.mfun.service.book.BookService;
import com.mfun.service.book.BookServiceImpl;
import com.mfun.service.cart.CartService;
import com.mfun.service.cart.CartServiceImpl;
import com.mfun.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();
    private BookService bookService = new BookServiceImpl();

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        Cart cart = ServletUtils.getCart(req);
        try {
            Book book = bookService.get(ServletUtils.param2bean(req, Book.class));
            cartService.addBook2Cart(cart, book);
            req.getSession().setAttribute("lastAdded", book.getTitle());
            resp.sendRedirect(req.getHeader("Referer"));
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cartService.deleteItem(cart, Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getHeader("Referer"));
    }

//    private void updateItem(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//        try {
//            int count =  Integer.parseInt(req.getParameter("count"));
//            if (count < 1) {
//                throw new NumberFormatException("数量小于1");
//            }
//            int id = Integer.parseInt(req.getParameter("id"));
//            Cart cart = (Cart) req.getSession().getAttribute("cart");
//            cartService.updateCount(cart, id, count);
//        } catch (NumberFormatException e) {
//            // 返回错误信息
////            System.out.println("count 格式错误");
//        }
//        resp.sendRedirect(req.getHeader("Referer"));
//    }

    /**
     * ajax版--更新购物车商品数量
     */
    protected void ajaxUpdateItem(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            int count =  Integer.parseInt(req.getParameter("count"));
            if (count < 1) {
                throw new NumberFormatException("数量小于1");
            }
            int id = Integer.parseInt(req.getParameter("id"));
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            cartService.updateCount(cart, id, count);
            // 返回 json 对象
            Map<String, Object> results = new HashMap<>();
            results.put("item_totalMoney", String.valueOf(cart.getItems().get(id).getPrice()));
            results.put("cart_totalMoney", cart.getTotalPrice());
            results.put("cart_totalCount", cart.getTotalCount());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(results);
            resp.setContentType("application/json");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println(jsonString);
            }
        } catch (NumberFormatException e) {
            // 返回错误信息
//            System.out.println("count 格式错误");
        }
    }

    private void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cartService.clearCart(cart);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
