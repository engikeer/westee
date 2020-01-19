package com.mfun.dao.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mfun.pojo.User;
import com.mfun.util.ConnectionUtil;
import org.junit.jupiter.api.Test;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void getLoginUserTest() throws SQLException {
        ConnectionUtil.connect();
        UserDao dao = new UserDaoImpl();
        User user = dao.getLoginUser("zhaoyan", "0000000");
        assertEquals(8, user.getId());
        ConnectionUtil.disconnect();
    }

    @Test
    public void getUserCountTest() throws SQLException {
        ConnectionUtil.connect();
        UserDao dao = new UserDaoImpl();
        assertEquals(2, dao.getUserCount("孙",3));
        assertEquals(0, dao.getUserCount("李明", 3));
        assertEquals(3, dao.getUserCount(null, 2));
        assertEquals(2, dao.getUserCount("赵", 0));
        ConnectionUtil.disconnect();
        System.out.println("结束");
    }
}
