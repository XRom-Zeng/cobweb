package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 图形验证码属性配置
 * @author: XRom
 * @createdTime: 2018-07-14 23:55:42
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.code.image")
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 图片宽度
     */
    private int width = 60;

    /**
     * 图片高度
     */
    private int height = 20;
}
