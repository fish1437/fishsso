package com.fishsso.permissiondock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/amsso")
public class LoginController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/test")
    public void test(HttpServletRequest request)
    {

        LOGGER.info("通过sso认证");

    }


}
