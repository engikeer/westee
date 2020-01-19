package com.mfun.service.role;

import com.mfun.dao.role.RoleDao;
import com.mfun.dao.role.RoleDaoImpl;
import com.mfun.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl() {
        this.roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() throws SQLException {
        return roleDao.getRoleList();
    }
}
