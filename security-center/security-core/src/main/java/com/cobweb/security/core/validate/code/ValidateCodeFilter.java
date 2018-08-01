package com.cobweb.security.core.validate.code;

import com.cobweb.security.core.constant.SecurityConstants;
import com.cobweb.security.core.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: XRom
 * @createdTime: 2018-07-13 20:14:21
 * 验证码过滤器
 */
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SecurityProperties securityProperties;

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);

        addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getSms().getUrls(), ValidateCodeType.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            try {
                validate(new ServletWebRequest(request), type);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验逻辑
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request, ValidateCodeType validateCodeType) throws ServletRequestBindingException {
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KET_PREFIX + validateCodeType);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "validCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KET_PREFIX + validateCodeType);
            throw new ValidateCodeException("验证码已经过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码输入错误");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KET_PREFIX + validateCodeType);
    }

    /**
     * 将系统中配置的需要验证码的地址增加到map
     * @param urlString 需要验证码的地址
     * @param codeType  验证码类型
     */
    private void addUrlToMap(String urlString, ValidateCodeType codeType) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrls(), ",");
            if (urls != null && urls.length > 0) {
                for (String url : urls) {
                    urlMap.put(url, codeType);
                }
            }
        }
    }

    /**
     * 获取验证码类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        for (Map.Entry<String, ValidateCodeType> enter : urlMap.entrySet()) {
            if (antPathMatcher.match(enter.getKey(), request.getRequestURI())) {
                return enter.getValue();
            }
        }
        return null;
    }
}
