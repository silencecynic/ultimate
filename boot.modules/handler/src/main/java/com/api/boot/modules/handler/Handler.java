package com.api.boot.modules.handler;


import com.api.boot.modules.domain.AuthcRole;
import com.api.boot.modules.infrastructure.http.HttpModel;
import com.api.boot.modules.infrastructure.http.MessagesResolve;
import com.api.boot.modules.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RequestMapping (value = "/api/v1")
@RestController
public class Handler {

    private HandlerService handlerService;

    @Autowired
    public Handler (HandlerService handlerService) {
        this.handlerService = handlerService;
    }

    @PostMapping (value = "/authc/role/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpModel authcRole(@PathVariable(value = "id") String id) {
        AuthcRole authcRole = new AuthcRole();
        System.out.println(MessagesResolve.get("title"));
        return handlerService.authc(authcRole);
    }


}
