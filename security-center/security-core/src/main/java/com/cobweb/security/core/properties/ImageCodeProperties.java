package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: XRom
 * @createdTime: 2018-07-14 23:55:42
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.code.image")
public class ImageCodeProperties {

    private int width = 60;

    private int height = 20;

    private int length = 4;

    private int expireTime = 60;

    private String urls;
}
