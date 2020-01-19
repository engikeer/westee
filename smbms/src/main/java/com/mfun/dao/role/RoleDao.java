package com.mfun.dao.role;

import com.mfun.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    /**
     * 获取所有角色的列表
     * @return 角色列表
     * @throws SQLException SQL 异常
     */
    public List<Role> getRoleList() throws SQLException;
}
