package com.designer.toys.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.designer.toys.user.dao")
public class DesignerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesignerUserApplication.class, args);
    }
}