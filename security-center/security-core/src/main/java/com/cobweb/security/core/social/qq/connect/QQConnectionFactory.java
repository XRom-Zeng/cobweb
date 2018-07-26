package com.cobweb.security.core.social.qq.connect;

import com.cobweb.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: XRom
 * @createdTime: 2018-07-18 17:39:53
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret ) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
