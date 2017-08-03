package com.nightcat.config.aop;

import com.nightcat.common.base.BaseObject;
import com.nightcat.common.constant.Constant;
import com.nightcat.common.utility.Util;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


/**
 * 记录Web服务日志
 */
@Aspect
@Component
@Order(0)
public class WebLogAspect extends BaseObject {


    @Pointcut("execution(public * com.nightcat.*.web..*.*(..))")
    public void webLog() {
    }


    private static class WebLog {
        String url;
        String method;
        String ip;
        String host;
        String token;
        Map<String, String[]> params;
        String class_method;
        String class_method_ars;
        long execution_time;
        Object return_value;

        public WebLog(HttpServletRequest request) {
            this.url = request.getRequestURL().toString();
            this.method = request.getMethod();
            this.ip = request.getRemoteAddr();
            this.host = request.getRemoteHost();
            this.token = request.getHeader(Constant.AUTHORIZATION);

            this.params = request.getParameterMap();
        }

        @Override
        public String toString() {
            return Util.toJson(this);
        }
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();


        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog log = new WebLog(request);
        log.class_method = pjp.getSignature().getDeclaringTypeName() +
                "." + pjp.getSignature().getName();
        log.class_method_ars = Arrays.toString(pjp.getArgs());


        Object result = null;
        try {
            result = pjp.proceed();
            log.return_value = result;
            return result;
        } finally {
            log.execution_time = System.currentTimeMillis() - startTime;
            logger.info("收到请求: ");
            logger.info(log.toString());
        }

    }
}
