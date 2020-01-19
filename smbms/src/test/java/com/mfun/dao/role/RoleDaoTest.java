package com.mfun.dao.role;

import com.mfun.pojo.Role;
import com.mfun.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class RoleDaoTest {

    @Test
    public void getRoleListTest() throws SQLException {
        ConnectionUtil.connect();
        RoleDao roleDao = new RoleDaoImpl();
        List<Role> roles = roleDao.getRoleList();
        roles.forEach(System.out::println);

        ConnectionUtil.disconnect();
    }
}
