package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: XRom
 * @createdTime: 2018-07-19 14:17:48
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.social.qq")
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
