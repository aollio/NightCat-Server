package com.nightcat.config.aop;

import com.nightcat.common.base.BaseObject;
import com.nightcat.common.constant.Constant;
import com.nightcat.common.utility.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.engine.transaction.jta.platform.internal.WeblogicJtaPlatform;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.serializer.RedisSerializer;
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
public class WebLogAspect extends BaseObject {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    ThreadLocal<WebLog> webLog = new ThreadLocal<>();

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


    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog log = new WebLog(request);
        log.class_method = joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName();
        log.class_method_ars = Arrays.toString(joinPoint.getArgs());
        logger.info("收到请求(未执行): " + log.toString());
        webLog.set(log);
    }


    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        WebLog log = webLog.get();
        log.return_value = ret;
        log.execution_time = System.currentTimeMillis() - startTime.get();
        logger.info("返回响应(执行后): " + log.toString());
    }

}
