package com.mfun.controller;

import com.mfun.pojo.Cart;
import com.mfun.pojo.Order;
import com.mfun.pojo.User;
import com.mfun.service.order.OrderService;
import com.mfun.service.order.OrderServiceImpl;
import com.mfun.utils.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    private void checkout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        // 由 LoginFilter 检查用户是否已登陆
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().size() == 0) {
            // 购物车为空，提示购物车为空
            return;
        }
        String orderId = orderService.checkout(cart, user);
        session.setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.getOrderForUser(user.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    private void receive(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String orderId = req.getParameter("orderId");
        orderService.updateStatus(orderId, OrderStatus.COMPLETED.getValue());
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
