package com.mfun.dao.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mfun.pojo.User;
import com.mfun.util.ConnectionUtil;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void getLoginUserTest() throws SQLException {
        ConnectionUtil.connect();
        UserDao dao = new UserDaoImpl();
        User user = dao.getLoginUser("zhaoyan");
        assertEquals(8, user.getId());
        ConnectionUtil.disconnect();
    }
}
