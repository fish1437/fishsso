package com.fishsso.permissiondock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.fishsso.permissiondock"})
@EnableTransactionManagement
public class PermissionApplication extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(PermissionApplication.class);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(PermissionApplication.class, args);
    }
}
