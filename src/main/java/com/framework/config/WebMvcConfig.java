package com.framework.config;

import com.framework.annotation.AuthorizationInterceptor;
import com.framework.annotation.CurrentUserMethodArgumentResolver;
import com.framework.annotation.EnumPramMethodArgumentResolver;
import com.framework.formatter.TimestampFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Aollio
 * @date 15/05/2017
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private EnumPramMethodArgumentResolver enumPramMethodArgumentResolver;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CrosInterceptorAdapter crosInterceptorAdapter;

    @Autowired
    private TimestampFormatter timestampFormatter;

    /**
     * 增加参数解析器
     *
     * @author Aollio
     * @date 15/05/2017
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(enumPramMethodArgumentResolver);
        argumentResolvers.add(currentUserMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 参数解析器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(timestampFormatter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crosInterceptorAdapter);
        registry.addInterceptor(authorizationInterceptor);
    }
}
