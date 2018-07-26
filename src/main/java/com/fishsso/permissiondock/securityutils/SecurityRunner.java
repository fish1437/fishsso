package com.fishsso.permissiondock.securityutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SecurityRunner implements CommandLineRunner
{

    @Autowired
    SecurityMemoryConstant securityMemoryConstant;

    @Override
    public void run(String... args) throws Exception
    {

        securityMemoryConstant.initIpSet();

        securityMemoryConstant.initUrlSet();

    }
}
