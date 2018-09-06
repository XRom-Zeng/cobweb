package com.cobweb.xrom.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-01 17:38:18
 */
@Component
public class MsgProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {

        kafkaTemplate.send("test","xrom");
        kafkaTemplate.send("test","xrog");
    }
}
