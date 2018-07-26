package com.fishsso.permissiondock.model;

import com.fishsso.permissiondock.enums.SecurityHttpStatusCode;


public class Response
{

    public Response() {
        this.status = SecurityHttpStatusCode.CODE_SUCCESS.getValue();
        this.message = SecurityHttpStatusCode.CODE_SUCCESS.getDesc();
    }

    public Response(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Response(Object data) {
        super();
        this.status = SecurityHttpStatusCode.CODE_SUCCESS.getValue();
        this.message = SecurityHttpStatusCode.CODE_SUCCESS.getDesc();
        this.data = data;
    }

    private String status;
    private String message;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
