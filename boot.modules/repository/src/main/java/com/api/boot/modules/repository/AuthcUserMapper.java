package com.api.boot.modules.repository;

import com.api.boot.modules.domain.AuthcUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthcUserMapper {

    int insert(AuthcUser record);

    int insertSelective(AuthcUser record);
}