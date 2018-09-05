package com.api.boot.modules.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthcRole implements Serializable {

    private String id;

    private Integer sort;

    private String role;

    private String lock;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public AuthcRole(String id, Integer sort, String role, String lock, Date createTime) {
        this.id = id;
        this.sort = sort;
        this.role = role;
        this.lock = lock;
        this.createTime = createTime;
    }

    public AuthcRole() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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
}