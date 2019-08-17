package com.base.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
@MapperScan("com.base.manager.dao")
public class ManagerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerServiceApplication.class, args);
    }
}
