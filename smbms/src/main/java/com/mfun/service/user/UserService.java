package com.mfun.service.user;

import com.mfun.pojo.User;

import java.sql.SQLException;

public interface UserService {
    public User login(String userCode, String password) throws SQLException;

    public boolean updatePassword(long id, String password) throws SQLException;
}
