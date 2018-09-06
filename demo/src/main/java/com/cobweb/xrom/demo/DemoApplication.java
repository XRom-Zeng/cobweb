package com.cobweb.xrom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-01 17:37:23
 */
@SpringBootApplication
@EnableKafka
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
