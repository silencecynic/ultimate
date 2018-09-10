package com.api.boot.modules.service.impl;

import com.api.boot.modules.domain.AuthcRole;
import com.api.boot.modules.infrastructure.aop.annotation.TargetDataSource;
import com.api.boot.modules.infrastructure.aop.router.DataType;
import com.api.boot.modules.infrastructure.http.HttpModel;
import com.api.boot.modules.repository.AuthcRoleMapper;
import com.api.boot.modules.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Service
public class HandlerServiceImpl implements HandlerService {

    private AuthcRoleMapper authcRoleMapper;

    @Autowired
    public HandlerServiceImpl (AuthcRoleMapper authcRoleMapper) {
        this.authcRoleMapper = authcRoleMapper;
    }


    @Override
    @Transactional(readOnly = false)
    @TargetDataSource (target = DataType.master)
    public HttpModel authc(AuthcRole model) {
        HttpModel httpModel = HttpModel.instance();
        model.setId(UUID.randomUUID().toString().trim().replaceAll("[\\-]","").toUpperCase());
        model.setCreateTime(new Date());
        model.setLock("unlock");
        model.setRole("root");
        model.setSort(0);
        int val = authcRoleMapper.insert(model);
        if (val > 0) {
            httpModel
                    .setMessages("OK")
                    .setStatus(HttpServletResponse.SC_OK)
                    .setResponses(model.getId())
                    .build();
        } else {
            httpModel
                    .setMessages("Error")
                    .setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .setResponses(1)
                    .build();
        }
        return httpModel;
    }
}
