package com.api.boot.modules.handler;


import com.api.boot.modules.domain.AuthcRole;
import com.api.boot.modules.infrastructure.http.HttpModel;
import com.api.boot.modules.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping (value = "/api/v1")
@RestController
public class Handler {

    private HandlerService handlerService;

    private RedisTemplate<String,Object> redis;

    @Autowired
    public Handler (HandlerService handlerService , RedisTemplate<String,Object> redis) {
        this.handlerService = handlerService;
        this.redis = redis;
    }

    @PostMapping (value = "/authc/role/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpModel authcRole(@PathVariable(value = "id") String id) {
        AuthcRole authcRole = new AuthcRole();
//        redis.opsForValue().set("vim","牧神记");
//        System.out.println(redis.opsForValue().get("vim"));
        return handlerService.authc(authcRole);
    }

}
