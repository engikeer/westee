package com.mfun.pojo;

import com.mfun.util.Gender;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
    private long id;
    private String userCode;
    private String userName;
    private String userPassword;
    private Gender gender;
    private Date birthday;
    private String phone;
    private String address;
    private long userRole;
    private long createdBy;
    private Date creationDate;  // 注意，数据库中的时间以美国东部时间保存，输出时会转为当前客户端的时区
    private Long modifyBy;  // 可能为 null
    private Date modifyDate;  // 可能为 null

    public int getAge() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(this.birthday);
        return now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    public String getRoleName() {
        return "";
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "User{" + id +
                ", 账号：'" + userCode + '\'' +
                ", 名称：'" + userName + '\'' +
                ", 性别：'" + gender.getDesc() + '\'' +
                ", 生日：'" + dateFormat.format(birthday) + '\'' +
                ", 创建时间：'" + dateTimeFormat.format(creationDate) + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserRole() {
        return userRole;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
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
