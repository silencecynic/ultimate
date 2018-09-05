package com.api.boot.modules.service;

import com.api.boot.modules.domain.AuthcRole;
import com.api.boot.modules.infrastructure.http.HttpModel;

public interface HandlerService {

    HttpModel authc(AuthcRole model);


}
