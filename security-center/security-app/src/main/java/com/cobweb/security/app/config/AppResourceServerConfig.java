package com.cobweb.security.app.config;

import com.cobweb.security.core.constant.SecurityConstants;
import com.cobweb.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-02 22:07:16
 * 自定义资源服务器
 */
@Configuration
@EnableResourceServer
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage(securityProperties.getBrowser().getLoginPage())  //登录拦截处理
                    .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)    //登录请求
                    .successHandler(authenticationSuccessHandler)   //登录成功处理器
                    .failureHandler(authenticationFailureHandler)   //登录失败处理器
                    .and()
                .authorizeRequests()
                    .antMatchers(
                            securityProperties.getBrowser().getLoginPage(),
                            "/error",
                            SecurityConstants.DEFAULT_GET_IMG__CODE_URL //获取图形验证码接口
                    ).permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf().disable();
    }
}
