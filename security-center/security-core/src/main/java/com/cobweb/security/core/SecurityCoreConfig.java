package com.cobweb.security.core;

import com.cobweb.security.core.properties.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties({
        SecurityProperties.class,
        ImageCodeProperties.class,
        BrowserProperties.class,
        SessionProperties.class,
        OAuth2Properties.class
})
public class SecurityCoreConfig {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }

}
