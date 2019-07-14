package com.fanhehe.message.constant;

import java.util.HashMap;

/**
 * Created by fanhehe on 14/07/2019.
 */
public enum ServiceEnum {
    User(1, "127.0.0.1:10014"),
    Message(2, "127.0.0.1:10015");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    ServiceEnum(int id, String endpoint) {
        this.id = id;
        this.endpoint = endpoint;
    }

    int id;
    String endpoint;
}
