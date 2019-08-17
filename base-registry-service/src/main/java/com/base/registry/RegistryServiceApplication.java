package com.base.registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistryServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RegistryServiceApplication.class).web(true).run(args);
    }
}
