package com.nightcat.config;

import com.nightcat.common.CatException;
import com.nightcat.common.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Object exceptionHandler(IOException e) {
        if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(e), "Broken pipe")) {
            return null;        //socket is closed, cannot return any response
        } else {
            return new HttpEntity<>(e.getMessage());
        }
    }

    /**
     * 系统异常处理，比如：404,500
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {


        Response response = new Response();
        response.setMessage(e.getMessage());

        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            response.setStatus(404);
        } else if (e instanceof CatException) {
            CatException sed = (CatException) e;
            response.setStatus(sed.getStatus());
            response.setMessage(sed.getMessage());
        } else {
            response.setStatus(500);
        }

        response.setContent(null);

        logger.error("出现异常! 异常返回信息: " + response.toString(), e);

        return response;
    }
}
