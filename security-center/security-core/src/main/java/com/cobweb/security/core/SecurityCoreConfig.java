package com.cobweb.security.core;

import com.cobweb.security.core.properties.BrowserProperties;
import com.cobweb.security.core.properties.ImageCodeProperties;
import com.cobweb.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        SecurityProperties.class,
        ImageCodeProperties.class,
        BrowserProperties.class
})
public class SecurityCoreConfig {
}
