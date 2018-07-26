package com.cobweb.security.core.social;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: XRom
 * @createdTime: 2018-07-18 17:47:04
 */
@EnableSocial
@Configuration
public class SocialConfig extends SocialConfigurerAdapter {

    @Bean
    public SpringSocialConfigurer cobwebSocialConfigurer() {
        return new SpringSocialConfigurer();
    }
}
