package com.cobweb.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XRom
 * @createdTime: 2018-08-09 01:15:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class AdminApplication {

    @Value("${env}")
    private String env;

    @GetMapping("env")
    public String getEnv() {
        return env;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
