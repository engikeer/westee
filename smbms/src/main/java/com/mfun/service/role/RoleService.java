package com.mfun.service.role;

import com.mfun.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    List<Role> getRoleList() throws SQLException;

}
