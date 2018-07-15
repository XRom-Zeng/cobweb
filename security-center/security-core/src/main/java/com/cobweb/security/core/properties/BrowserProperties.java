package com.cobweb.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cobweb.security.browser")
public class BrowserProperties {

    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;
}
