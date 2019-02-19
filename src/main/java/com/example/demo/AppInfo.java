package com.example.demo;

import java.io.Serializable;

public class AppInfo implements Serializable {
    private String message;
    private String index;
    private String host;
    private String java;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }
}
