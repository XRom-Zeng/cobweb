package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-03 02:40:25
 */
@Data
@ConfigurationProperties(prefix = "cobweb.security.oauth2")
public class OAuth2Properties {

    private String jwtSigningKey = "XRom";  //jwt签名秘钥

    private StoreType storeType;   //token存储类型
}
