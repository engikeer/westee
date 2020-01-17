package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.util.Map;
import java.sql.Date;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(String userCode) {
        return null;
    }

    private User rowToUser(Map<String, Object> row) {
        User user = new User();
        user.setId((int) row.get("id"));
        user.setUserCode((String) row.get("userCode"));
        user.setUserName((String) row.get("userName"));
        user.setUserPassword((String) row.get("userPassword"));
        user.setGender((int) row.get("gender"));
        user.setBirthday(new java.util.Date(((Date) row.get("birthday")).getTime()));
        user.setPhone((String) row.get("phone"));
        user.setAddress((String) row.get("address"));
        user.setUserRole((int) row.get("userRole"));
        return null;
    }
}
