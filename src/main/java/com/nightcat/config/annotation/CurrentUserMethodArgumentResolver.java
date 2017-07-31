package com.nightcat.config.annotation;

import com.nightcat.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @author finderlo
 * @date 15/05/2017
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

//    @Autowired
//    UserDao userDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //取出存入的用户ID
//        String currentUserId = (String) webRequest.getAttribute(Constant.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
//        if (currentUserId != null) {
//            return userDao.findById(currentUserId);
//        }
        return new MissingServletRequestPartException(Constant.CURRENT_USER_ID);
    }
}
