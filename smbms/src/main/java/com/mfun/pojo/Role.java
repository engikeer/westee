package com.mfun.pojo;

import java.util.Date;

public class Role {
    private long id;
    private String roleCode;
    private String roleName;
    private long createBy;
    private Date creationDate;
    private Long modifyBy;  // 可能为 null
    private Date modifyDate; // 可能为 null

    @Override
    public String toString() {
        return "Role{" +
                "角色 id：" + id +
                ", 角色代码：'" + roleCode + '\'' +
                ", 角色名：'" + roleName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
