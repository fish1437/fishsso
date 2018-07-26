package com.fishsso.permissiondock.securityutils;

import com.am.amcache.service.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class SecurityMemoryConstant
{

    //系统启动时初始化，ip白名单变化时走MQ消费监听
    private static Set<String> ipSet = new HashSet<String>();

    //系统启动时初始化，路由白名单变化时走MQ消费监听
    private static Set<String> urlSet = new HashSet<String>();


    void initIpSet()
    {

        //System.out.println("init ip set");
        //System.out.println("缓存获取ip白名单为："+redisClient.getString("ceshi20182224646",2));

    }

    void initUrlSet()
    {

        //System.out.println("init url set");
        //System.out.println("缓存获取url白名单为："+redisClient.getString("ceshi2018",2));

    }
}
