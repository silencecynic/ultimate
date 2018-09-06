package com.api.boot.modules.authc.chain;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;

import java.util.Map;

public abstract class AbstractChain extends DefaultShiroFilterChainDefinition implements Chain {


    @Override
    public void addPathDefinitions(Map<String, String> pathDefinitions) {
        super.addPathDefinitions(pathDefinitions);
    }

    @Override
    public void addPathDefinition(String antPath, String definition) {
        super.addPathDefinition(antPath, definition);
    }

    @Override
    public Map<String, String> getFilterChainMap() {
        return super.getFilterChainMap();
    }

    @Override
    public void load(Map<String, Object> chain) {

    }

    @Override
    public void remove(Map<String, Object> chain) {

    }


}
