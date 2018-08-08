package com.cobweb.security.browser;

import com.cobweb.security.browser.authentication.BrowserAuthenticationFailureHandler;
import com.cobweb.security.browser.authentication.BrowserAuthenticationSuccessHandler;
import com.cobweb.security.browser.session.CobwebExpiredSessionStrategy;
import com.cobweb.security.core.SmsCodeAuthenticationSecurityConfig;
import com.cobweb.security.core.ValidateCodeSecurityConfig;
import com.cobweb.security.core.constant.SecurityConstants;
import com.cobweb.security.core.properties.SecurityProperties;
import com.cobweb.security.core.properties.SessionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class SecurityBrowserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SessionProperties sessionProperties;

    @Autowired
    private BrowserAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private BrowserAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService UserDetailsService;

    @Autowired
    private SpringSocialConfigurer cobwebSocialConfigurer;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(validateCodeSecurityConfig) //验证码
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig) //短信认证
                    .and()
                .apply(cobwebSocialConfigurer)  //social
                    .and()
                .formLogin()
                    .loginPage(securityProperties.getBrowser().getLoginPage())  //登录页面
                    .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)    //登录请求
                    .successHandler(authenticationSuccessHandler)   //登录成功处理器
                    .failureHandler(authenticationFailureHandler)   //登录失败处理器
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(UserDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionUrl(SecurityConstants.DEFAULT_SESSION_INVALID_URL)   //session失效处理
                    .maximumSessions(sessionProperties.getMaximumSessions())    //最大session个数
                    .maxSessionsPreventsLogin(sessionProperties.isMaxSessionsPreventsLogin()) //当达到最大session登录数之后阻止其他登录
                    .expiredSessionStrategy(new CobwebExpiredSessionStrategy()) //session终止
                    .and()
                    .and()
                .authorizeRequests()
                    .antMatchers(
                            securityProperties.getBrowser().getLoginPage(),
                            "/error",
                            SecurityConstants.DEFAULT_GET_IMG__CODE_URL, //获取图形验证码接口
                            SecurityConstants.DEFAULT_GET_SMS__CODE_URL,    //获取短信验证码接口
                            SecurityConstants.DEFAULT_SESSION_INVALID_URL   //session失效处理接口
                    ).permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf().disable();
    }
}
