package com.cobweb.security.core.social.qq.api;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author: XRom
 * @createdTime: 2018-07-17 18:41:09
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    /* 获取openid请求地址 */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /* 获取用户信息请求地址 */
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String response = getRestTemplate().getForObject(url, String.class);
        QQUserInfo userInfo = new Gson().fromJson(response, QQUserInfo.class);
        userInfo.setOpenId(openId);
        return userInfo;
    }
}
