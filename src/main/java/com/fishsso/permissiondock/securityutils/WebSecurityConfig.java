package com.fishsso.permissiondock.securityutils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public SecurityInterceptor getSecurityInterceptor()
    {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //放行配置
        addInterceptor.excludePathPatterns("/amsso/**", "/ssoapi/**", "/error");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");


    }

}
