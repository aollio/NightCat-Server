package com.nightcat.common.utility;


import com.nightcat.common.CatException;
import com.nightcat.common.ErrorCode;
import com.nightcat.common.constant.HttpStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author finderlo
 * @date 16/05/2017
 */
public class Assert {

    public static void notNull(Object o, int status, String message) {
        if (o == null) {
            throw new CatException(status, message);
        }
    }

    public static void isNull(Object o, String message) {
        isNull(o, HttpStatus.BAD_REQUEST, message);
    }

    public static void isNull(Object o, int status, String message) {
        if (o != null) {
            throw new CatException(status, message);
        }
    }

    public static void isTrue(boolean b, String message) {
        if (!b) {
            throw new CatException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void isTrue(boolean b, int status, String message) {
        if (!b) {
            throw new CatException(status, message);
        }
    }

    public static void strExist(String s) {
        if (s == null || "".equals(s.trim())) {
            throw new IllegalArgumentException();
        }
    }

    public static void strExist(String s, int status, String message) {
        if (s == null || "".equals(s.trim())) {
            throw new CatException(status, message);
        }
    }


    public static void checkNotEqual(@Null Object type, @NotNull Object target, ErrorCode errorCode) {
        if (!target.equals(type)) {
            throw new CatException(errorCode);
        }
    }


}
