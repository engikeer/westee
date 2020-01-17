package com.mfun.service.user;

import com.mfun.dao.user.UserDao;
import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) throws SQLException {
        User loginUser = userDao.getLoginUser(userCode);
        if (loginUser == null || !loginUser.getUserPassword().equals(password)) {
            return null;
        }
        return loginUser;
    }
}
