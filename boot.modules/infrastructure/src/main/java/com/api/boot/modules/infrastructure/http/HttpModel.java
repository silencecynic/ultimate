package com.api.boot.modules.infrastructure.http;

import java.io.Serializable;

public class HttpModel implements Serializable {


    private static volatile HttpModel instance = null;

    public static HttpModel instance() {
        if (instance == null) {
            synchronized (HttpModel.class) {
                if (instance == null) {
                    instance = new HttpModel();
                }
            }
        }
        return instance;
    }

    private HttpModel () { }

    private String messages;

    private Integer status;

    private Object responses;

    public String getMessages() {
        return messages;
    }

    public HttpModel setMessages(String messages) {
        this.messages = messages;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public HttpModel setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Object getResponses() {
        return responses;
    }

    public HttpModel setResponses(Object responses) {
        this.responses = responses;
        return this;
    }

    public HttpModel build() {
        return this;
    }

}
