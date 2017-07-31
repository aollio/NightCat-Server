package com.nightcat.common;

import com.google.gson.Gson;

/**
 * Created by finderlo on 2017/4/7.
 */
public class SimResponse {

    /**
     * 状态码
     */
    private int status;

    /**
     * 描述
     */
    private String message;

    /**
     * 返回信息
     */
    private Object content;

    /**
     * @author finderlo
     * @date 17/04/2017
     */
    public SimResponse() {
        this(200, null);
    }

    public SimResponse(int status, String message, Object content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }


    private SimResponse(int status, Object content) {
        this.status = status;
        this.content = content;
    }

    /**
     * @author finderlo
     * @date 17/04/2017
     */
    private SimResponse(ErrorCode errorCode) {
        this(errorCode, null);
    }


    private SimResponse(ErrorCode errorCode, Object content) {
        this.status = errorCode.getCode();
        this.message = errorCode.getDescription();
        this.content = content;
    }



    /**
     * @param object
     * @return 返回一个承载对象的response.
     **/
    public static SimResponse withObject(final Object object) {
        return new SimResponse(ErrorCode.DEFAULT_SUCCESS, object);
    }

    /**
     * @return 返回一个默认成功的response.
     **/
    public static SimResponse ok() {
        return new SimResponse(ErrorCode.DEFAULT_SUCCESS);
    }

    public static SimResponse ok(final Object object) {
        return new SimResponse(ErrorCode.DEFAULT_SUCCESS, object);
    }


    /**
     * @return 返回一个默认错误的response.
     **/
    public static SimResponse error(int status, String message) {
        return new SimResponse(status,message,null);
    }

    /**
     * @return 返回一个默认错误的response.
     **/
    public static SimResponse error() {
        return new SimResponse(ErrorCode.DEFAULT_ERROR);
    }

    public static SimResponse error(ErrorCode errorCode) {
        return new SimResponse(errorCode);
    }


    /**
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * @return
     */
    public Object getContent() {
        return content;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
