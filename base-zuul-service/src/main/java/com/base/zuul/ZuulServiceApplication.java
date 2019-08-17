package com.base.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class ZuulServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulServiceApplication.class).web(true).run(args);
    }
}
