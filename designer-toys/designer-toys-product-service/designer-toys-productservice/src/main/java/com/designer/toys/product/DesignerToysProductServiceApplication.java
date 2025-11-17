package com.designer.toys.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.designer.toys.product.dao")
public class DesignerToysProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignerToysProductServiceApplication.class, args);
    }

}
