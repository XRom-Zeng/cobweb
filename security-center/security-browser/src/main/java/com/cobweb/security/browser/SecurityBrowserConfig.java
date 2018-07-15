package com.cobweb.security.browser;

import com.cobweb.security.browser.authentication.BrowserAuthenticationFailureHandler;
import com.cobweb.security.browser.authentication.BrowserAuthenticationSuccessHandler;
import com.cobweb.security.core.properties.SecurityProperties;
import com.cobweb.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityBrowserConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private BrowserAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private BrowserAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage(securityProperties.getBrowser().getLoginPage())
                .loginProcessingUrl("/security/browser/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(
                        securityProperties.getBrowser().getLoginPage(),
                        "/error",
                        "/security/core/code/image"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
