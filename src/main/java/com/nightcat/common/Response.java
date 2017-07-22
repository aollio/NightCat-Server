package com.nightcat.common;

public class Response {


    /**
     * 状态码
     */
    private int status;

    /**
     * 描述
     */
    private String message;

    /**
     * 实际内容
     */
    private Object data;

    public Response() {
        this(null);
    }

    private Response(Object data) {
        this(200, data);
    }


    private Response(int status, Object data) {
        this(status, null, data);
    }

    private Response(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static Response error(int status, String message) {
        return new Response(status, message, null);
    }

    public static Response error(int status) {
        return new Response(status, null);
    }


    public static Response ok() {
        return new Response();
    }

    public static Response ok(Object content) {
        return new Response(content);
    }

    public static Response ok(int status, Object content) {
        return new Response(status, content);
    }


    public int getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }


    public Object getData() {
        return data;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
