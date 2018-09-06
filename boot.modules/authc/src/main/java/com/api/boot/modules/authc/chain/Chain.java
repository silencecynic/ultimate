package com.api.boot.modules.authc.chain;

import java.util.Map;

public interface Chain {

    void remove(Map<String,Object> chain);

    void load(Map<String,Object> chain);


}
