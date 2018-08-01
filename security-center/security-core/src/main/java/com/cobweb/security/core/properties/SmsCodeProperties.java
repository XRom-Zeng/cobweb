package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信验证码属性配置
 * @author: XRom
 * @createdTime: 2018-07-29 23:02:50
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.code.sms")
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 验证码过期时间（单位：秒）
     */
    private int expireTime = 60;

    /**
     * 操作场景：多个地址以','隔开
     */
    private String urls;

}
