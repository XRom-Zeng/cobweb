package com.cobweb.security.core.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author: XRom
 * @createdTime: 2018-07-25 12:14:52
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.setUseParametersForClientAuthentication(true);
    }

    /**
     * 获取令牌
     * @param accessTokenUrl    获取令牌地址
     * @param parameters    请求参数
     * @return
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String response = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        String[] item = StringUtils.splitByWholeSeparatorPreserveAllTokens(response, "&");
        /* 授权令牌，Access_Token */
        String access_token = StringUtils.substringAfterLast(item[0], "=");
        /* 该access token的有效期，单位为秒。 */
        Long expires_in = Long.valueOf(StringUtils.substringAfterLast(item[1], "="));
        /* 在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。 */
        String refresh_token = StringUtils.substringAfterLast(item[1], "=");
        return new AccessGrant(access_token, null, refresh_token, expires_in);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        /* 添加一个新的converters, 用于处理text/html这种contentType */
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
