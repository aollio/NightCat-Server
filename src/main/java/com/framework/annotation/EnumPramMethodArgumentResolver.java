package com.framework.annotation;

import com.nightcat.common.CatException;
import com.nightcat.common.constant.HttpStatus;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @author finderlo
 * @date 15/05/2017
 */
@Component
public class EnumPramMethodArgumentResolver implements HandlerMethodArgumentResolver {


    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EnumPramMethodArgumentResolver.class);

    EnumPramMethodArgumentResolver() {
        logger.info("初始化: " + this.getClass().getCanonicalName());
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(EnumParam.class)
                ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        logger.info("开始执行方法参数解析");
        String name = parameter.getParameterName();
        int origin = -1;
        try {
            origin = Integer.parseInt(webRequest.getParameter(name));
        } catch (Exception e) {
            throw new CatException(HttpStatus.BAD_REQUEST, "the param " + name + " is wrong number");
        }
        Class<?> enum1 = parameter.getParameterType();
        for (Object o : enum1.getEnumConstants()) {
            Enum e = (Enum) o;
            if (e.ordinal() == origin) {
                return e;
            }
        }
        throw new CatException(HttpStatus.BAD_REQUEST, "the param " + name + " has a wrong enum type origin value. Enum :" + parameter.getParameterType().getCanonicalName());
    }
}
