package com.framework.config

import com.yemao.common.CatException
import com.yemao.common.Response
import com.yemao.common.base.BaseObject
import com.yemao.common.constant.HttpStatus.BAD_REQUEST
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.NoHandlerFoundException
import java.io.IOException
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class GlobalExceptionHandler : BaseObject() {


    @ExceptionHandler(IOException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun exceptionHandler(e: IOException): Any? {
        return if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(e), "Broken pipe")) {
            null        //socket is closed, cannot return any response
        } else {
            HttpEntity<String>(e.message)
        }
    }

    /**
     * 系统异常处理，比如：404,500
     */
    @ExceptionHandler(value = Exception::class)
    @ResponseBody
    @Throws(Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): Response {
        val response = Response()
        response.message = e.message
        when (e) {
            is NoHandlerFoundException -> response.status = 404
            is CatException -> response.status = e.status
            is MissingServletRequestParameterException -> response.status = BAD_REQUEST
            is IllegalArgumentException -> response.status = BAD_REQUEST
            is kotlin.KotlinNullPointerException -> response.status = BAD_REQUEST
            else -> response.status = 500
        }
        response.content = null
        logger.error("出现异常! 异常返回信息: " + response.toString(), e)
        return response
    }
}

