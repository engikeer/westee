package com.mfun.controller;

import com.mfun.pojo.Order;
import com.mfun.service.order.OrderService;
import com.mfun.service.order.OrderServiceImpl;
import com.mfun.utils.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Order> orders = orderService.getAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }


    private void deliver(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String orderId = req.getParameter("orderId");
        orderService.updateStatus(orderId, OrderStatus.FILLED.getValue());
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}
