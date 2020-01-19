package com.mfun.service.user;

import com.mfun.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User login(String userCode, String password) throws SQLException;

    boolean updatePassword(long id, String password) throws SQLException;

    int getUserCount(String username, int userRole) throws SQLException;

    List<User> getUserList(
            String username, int userRole, int currentPageNo, int pageSize)
            throws SQLException;
}
