package com.nightcat.common;

/**
 * @author finderlo
 * @date 20/04/2017
 */
public class CatException extends RuntimeException {


    private int status = 500;

    public CatException(String message) {
        this(500, message, null);
    }

    public CatException(int status, String message) {
        this(status, message, null);
    }


    public CatException(int status, String message, Exception e) {
        super(message, e);
        this.status = status;
    }

    public CatException(ErrorCode errorCode, Exception e) {
        this(errorCode.getCode(), errorCode.getDescription(), e);
    }

    public CatException(ErrorCode errorCode) {
        this(errorCode, null);
    }


    public int getStatus() {
        return status;
    }
}
