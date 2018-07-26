package com.fishsso.permissiondock.securityutils;


import com.alibaba.fastjson.JSON;
import com.fishsso.permissiondock.enums.SecurityHttpStatusCode;
import com.fishsso.permissiondock.model.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter
{


    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Autowired
    RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {


        LOGGER.debug(request.getRequestURL() + JSON.toJSONString(request.getParameterMap()));

        Long startTime = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();

        map = getHeadersInfo(request);

        String access_token = map.get("accesstoken");

        boolean tag = false;

        //有token，校验是否有效
        if (StringUtils.isNotBlank(access_token))
        {

            //校验session中token是否存在
            //有效：放行
            if (StringUtils.isNotBlank(redisClient.get(access_token)))
            {

                tag = super.preHandle(request, response, handler);

            } else
            { //无效

                if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
                {

                    tag = dealAjax(request, response, handler);

                } else
                {  //当前请求token失效

                    tag = dealFrom(request, response, handler);

                }
            }

            //请求
        } else
        {

            //token为空
            //无效
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
            {

                tag = dealAjax(request, response, handler);

            } else
            {  //当前请求token失效

                tag = dealFrom(request, response, handler);

            }

        }

        Long endTime = System.currentTimeMillis();

        LOGGER.debug("本次请求执行时间：" + (endTime - startTime) + "ms");

        return tag;


    }

    private boolean dealAjax(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        response.setCharacterEncoding("utf-8");

        response.setContentType("text/json");

        PrintWriter out = response.getWriter();

        Response amResponse = new Response();

        amResponse.setStatus(SecurityHttpStatusCode.CODE_USER_NOT_LOGIN.getValue());

        amResponse.setMessage(SecurityHttpStatusCode.CODE_USER_NOT_LOGIN.getDesc());

        out.write(JSON.toJSONString(amResponse));

        out.flush();

        return false;
    }

    private boolean dealFrom(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {


        PrintWriter out = response.getWriter();

        out.write("<script>window.parent.location.href='https://baidu.com?rediectUrl=" + request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : "") + "'</script>");

        out.flush();

        return false;
    }


    private static Map<String, String> getHeadersInfo(HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
