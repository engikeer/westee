package com.mfun.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfun.pojo.Role;
import com.mfun.pojo.User;
import com.mfun.service.role.RoleService;
import com.mfun.service.role.RoleServiceImpl;
import com.mfun.service.user.UserService;
import com.mfun.service.user.UserServiceImpl;
import com.mfun.util.ControllerEnum;
import com.mfun.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("savepwd".equals(method)) {
            updatePassword(req, resp);
        } else if ("pwdmodify".equals(method)) {
            pwdModify(req, resp);
        } else if ("query".equals(method)) {
            query(req, resp);
        }
    }

    /**
     * 查询用户信息
     * @param req 请求
     * @param resp 响应
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) {
        // 从前端获取参数
        String queryname = req.getParameter("queryname");
        if ("".equals(queryname)) {
            queryname = null;
        }
        int queryUserRole = 0;
        if (!StringUtils.isNullOrEmpty(req.getParameter("queryUserRole"))) {
            queryUserRole = Integer.parseInt(req.getParameter("queryUserRole"));
        }
        int currentPageNo = 1;
        if (!StringUtils.isNullOrEmpty(req.getParameter("pageIndex"))) {
            currentPageNo = Integer.parseInt(req.getParameter("pageIndex"));
        }

        int pageSize = 5;  // 首次访问时显示的条目数，可从配置文件中读取

        // 获取用户总数
        UserService userService = new UserServiceImpl();
        try {
            int userCount = userService.getUserCount(queryname, queryUserRole);
            PageSupport pageSupport = new PageSupport();
            pageSupport.setPageSize(pageSize);
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setTotalCount(userCount);

            // 控制首页和尾页
            int totalPageCount = pageSupport.getTotalPageCount();
            // 页码小于 1，显示第一页
            if (totalPageCount < 1) {
                currentPageNo = 1;
            } else if (currentPageNo > totalPageCount) {
                currentPageNo = totalPageCount;
            }
            List<User> users = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
            req.setAttribute("userlist", users);

            RoleService roleService = new RoleServiceImpl();
            List<Role> roles = roleService.getRoleList();
            req.setAttribute("roleList", roles);
            req.setAttribute("totalPageCount", totalPageCount);
            req.setAttribute("totalCount", userCount);
            req.setAttribute("currentPageNo", currentPageNo);
            req.setAttribute("queryUserName", queryname);
            req.setAttribute("queryUserRole", req.getParameter("queryUserRole"));

            req.getRequestDispatcher("/jsp/userlist.jsp").forward(req, resp);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 验证旧密码
     * @param req 请求
     * @param resp 响应
     */
    private void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object attribute = req.getSession().getAttribute(ControllerEnum.USER.getValue());

        String oldPassword = req.getParameter("oldpassword");

        Map<String, String> results = new HashMap<>();
        if (attribute == null) {
            results.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldPassword)){
            results.put("result", "error");
        } else {
            User user = (User) attribute;
            String userPassword = user.getUserPassword();
            if (oldPassword.equals(userPassword)) {
                results.put("result", "true");
            } else {
                results.put("result", "false");
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(results);
        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(jsonString);
        }
    }

    /**
     * 修改密码
     * @param req 请求
     * @param resp 馅饼
     * @throws ServletException Servlet 异常
     * @throws IOException IO 异常
     */
    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(ControllerEnum.USER.getValue());

        String password = req.getParameter("newpassword");

        if (attribute == null || password == null) {
            req.setAttribute(ControllerEnum.MESSAGE.getValue(), "新密码无法使用");
        } else {
            User user = (User) attribute;
            UserService userService = new UserServiceImpl();
            try {
                if (userService.updatePassword(user.getId(), password)) {
                    req.setAttribute(ControllerEnum.MESSAGE.getValue(), "密码修改成功，请使用新密码重新登陆");
                    // 移除 session
                    req.getSession().removeAttribute(ControllerEnum.USER.getValue());
                } else {
                    req.setAttribute(ControllerEnum.MESSAGE.getValue(), "密码修改失败");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
    }
}
