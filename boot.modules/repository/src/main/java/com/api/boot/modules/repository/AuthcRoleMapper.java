package com.api.boot.modules.repository;


import com.api.boot.modules.domain.AuthcRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthcRoleMapper {

    int insert(AuthcRole record);

    int insertSelective(AuthcRole record);
}