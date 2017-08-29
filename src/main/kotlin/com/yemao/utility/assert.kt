package com.yemao.utility

import com.yemao.common.CatException
import com.yemao.common.ErrorCode
import com.yemao.common.constant.HttpStatus
import com.yemao.users.models.Role
import com.yemao.users.models.User
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 * @author finderlo
 * @date 16/05/2017
 */
object Assert {

    fun notNull(o: Any?, status: Int, message: String) {
        if (o == null) {
            throw CatException(status, message)
        }
    }

    fun equals(src: Any?, tar: Any?, status: Int, message: String) {
        if (src != tar) {
            throw CatException(status, message)
        }
    }


    fun isNull(o: Any?, status: Int = HttpStatus.BAD_REQUEST, message: String) {
        if (o != null) {
            throw CatException(status, message)
        }
    }

    fun isTrue(b: Boolean, status: Int = HttpStatus.BAD_REQUEST, message: String) {
        if (!b) {
            throw CatException(status, message)
        }
    }


    fun isFalse(b: Boolean, status: Int, message: String) {
        if (b) {
            throw CatException(status, message)
        }
    }


    fun strExist(s: String?, status: Int, message: String) {
        if (s == null || "" == s.trim { it <= ' ' }) {
            throw CatException(status, message)
        }
    }


    fun checkNotEqual(@Null type: Any, @NotNull target: Any, errorCode: ErrorCode) {
        if (target != type) {
            throw CatException(errorCode)
        }
    }

    fun isEmployer(user: User, status: Int, message: String) {
        isRole(user, Role.EMPLOYER, status, message)
    }

    fun isDesigner(user: User, status: Int, message: String) {
        isRole(user, Role.DESIGNER, status, message)
    }

    fun isRole(user: User, role: Role, status: Int, message: String) {
        if (user.role !== role) {
            throw CatException(status, message)
        }
    }

    fun notZero(value: Int?, status: Int, message: String) {
        if (value == null || value == 0) {
            throw CatException(status, message)
        }
    }

    fun notZero(value: Double?, status: Int, message: String) {
        if (value == null || value == 0.0) {
            throw CatException(status, message)
        }
    }


}
