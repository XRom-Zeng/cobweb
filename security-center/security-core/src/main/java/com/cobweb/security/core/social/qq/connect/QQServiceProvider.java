package com.cobweb.security.core.social.qq.connect;

import com.cobweb.security.core.social.qq.api.QQ;
import com.cobweb.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author: XRom
 * @createdTime: 2018-07-17 19:24:51
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    /* 引导用户跳转地址 */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /* 获取令牌地址 */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    /**
     *
     * @param accessToken   令牌
     * @return
     */
    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
