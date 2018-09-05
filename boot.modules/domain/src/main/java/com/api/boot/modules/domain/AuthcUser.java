package com.api.boot.modules.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthcUser implements Serializable {

    private String id;

    private Integer sort;

    private String userName;

    private String password;

    private String lock;

    private Date createTime;

    private String salt;

    private static final long serialVersionUID = 1L;

    public AuthcUser(String id, Integer sort, String userName, String password, String lock, Date createTime, String salt) {
        this.id = id;
        this.sort = sort;
        this.userName = userName;
        this.password = password;
        this.lock = lock;
        this.createTime = createTime;
        this.salt = salt;
    }

    public AuthcUser() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock == null ? null : lock.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}