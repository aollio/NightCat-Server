package com.nightcat.config;

import com.nightcat.common.CatException;
import com.nightcat.common.Response;
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
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);

        Response response = new Response();
        response.setMessage(e.getMessage());

        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            response.setStatus(404);
        }else if (e instanceof CatException){
            CatException sed = (CatException) e;
            response.setStatus(sed.getStatus());
            response.setMessage(sed.getMessage());
        } else {
            response.setStatus(500);
        }

        response.setContent(null);
        return response;
    }
}
