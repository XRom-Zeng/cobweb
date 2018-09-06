package com.cobweb.xrom.demo;

import com.cobweb.xrom.demo.kafka.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-01 17:41:28
 */
@Order(1)
@Component
public class Demo implements CommandLineRunner {

    @Autowired
    private MsgProducer msgProducer;

    @Override
    public void run(String... args) throws Exception {
        msgProducer.send();
    }
}
