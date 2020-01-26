package com.mfun.dao.user;

import com.mfun.dao.BaseDaoImpl;
import com.mfun.pojo.User;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getUserByNameAndPassword(User user) throws SQLException {
        String sql = "SELECT * FROM bs_user WHERE username = ? AND password = ?";
        return getBean(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO bs_user VALUES(NULL, ?, ?, ?)";
        int rowCount = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return rowCount > 0;
    }

    @Override
    public boolean deleteUserByNameAndPassword(User user) throws SQLException {
        String sql  = "DELETE FROM bs_user WHERE username = ? AND PASSWORD = ?";
        int rowCount = update(sql, user.getUsername(), user.getPassword());
        return rowCount > 0;
    }
}
