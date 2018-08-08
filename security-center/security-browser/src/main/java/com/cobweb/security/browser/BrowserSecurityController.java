package com.cobweb.security.browser;

import com.cobweb.security.core.constant.SecurityConstants;
import com.cobweb.security.core.properties.SecurityProperties;
import com.cobweb.security.core.support.ApiResponse;
import com.cobweb.security.core.support.ResponseCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cobweb.security.core.support.ApiResponse.error;

@RestController
@RequestMapping("security/browser")
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("loginPage")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return error(ResponseCode.UNAUTHORIZED);
    }

    /**
     * session过期处理
     * @return
     */
    @GetMapping(SecurityConstants.SESSION_INVALID_ULR_SUFFIX)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse sessionInvalid() {
        return error("当前session已失效，请重新登录");
    }
}
