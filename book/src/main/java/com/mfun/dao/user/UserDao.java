package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.sql.SQLException;

public interface UserDao {

    User getUserByNameAndPassword(User user) throws SQLException;

    boolean insertUser(User user) throws SQLException;

    boolean deleteUserByNameAndPassword(User user) throws SQLException;
}
