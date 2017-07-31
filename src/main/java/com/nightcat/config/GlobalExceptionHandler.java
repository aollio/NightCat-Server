package com.nightcat.config;

import com.nightcat.common.CatException;
import com.nightcat.common.SimResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public SimResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);

        SimResponse simResponse = new SimResponse();
        simResponse.setMessage(e.getMessage());

        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            simResponse.setStatus(404);
        }else if (e instanceof CatException){
            CatException sed = (CatException) e;
            simResponse.setStatus(sed.getStatus());
            simResponse.setMessage(sed.getMessage());
        } else {
            simResponse.setStatus(500);
        }

        simResponse.setContent(null);
        return simResponse;
    }
}
