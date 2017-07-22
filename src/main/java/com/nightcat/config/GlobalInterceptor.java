package com.nightcat.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author finderlo
 * @date 15/05/2017
 */
@Component
public class GlobalInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        response.addHeader("'Access-Control-Allow-Methods","POST");
        System.out.println(Arrays.toString(request.getParameterMap().keySet().toArray()));
//        System.out.println(request);
        System.out.println(request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);

        System.out.println("ok");
    }

}


