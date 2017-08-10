package com.nightcat.common.utility;


import com.nightcat.common.CatException;
import com.nightcat.common.ErrorCode;
import com.nightcat.common.constant.HttpStatus;
import com.nightcat.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Locale;
import java.util.Objects;

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

    public static void equals(Object src, Object tar, int staus, String message) {
        if (!Objects.equals(src, tar)) {
            throw new CatException(staus, message);
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


    public static void isFalse(boolean b, String message) {
        if (b) {
            throw new CatException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void isFalse(boolean b, int status, String message) {
        if (b) {
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

    public static void isEmployer(User user, int status, String message) {
        isRole(user, User.Role.EMPLOYER, status, message);
    }

    public static void isDesigner(User user, int status, String message) {
        isRole(user, User.Role.DESIGNER, status, message);
    }

    public static void isRole(User user, User.Role role, int status, String message) {
        if (user.getRole() != role) {
            throw new CatException(status, message);
        }
    }

    public static void notZero(int value, int status, String message) {
        if (value == 0) {
            throw new CatException(status, message);
        }
    }

    public static void notZero(double value, int status, String message) {
        if (value == 0) {
            throw new CatException(status, message);
        }
    }


}
