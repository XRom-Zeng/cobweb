package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: XRom
 * @createdTime: 2018-08-02 12:14:07
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.session")
public class SessionProperties {

    /**
     * 一个用户最大session个数
     */
    private int maximumSessions = 1;

    /**
     *  当达到最大session登录数之后阻止其他登录
     */
    private boolean maxSessionsPreventsLogin = false;

}
