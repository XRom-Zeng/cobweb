package com.cobweb.security.browser.session;

import com.cobweb.security.core.support.ApiResponse;
import com.google.gson.Gson;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author: XRom
 * @createdTime: 2018-08-02 12:00:59
 */
public class CobwebExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private Gson gson = new Gson();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        System.out.println(event.getSessionInformation());
        event.getResponse().setContentType("application/json;charset=utf-8");
        event.getResponse().getWriter().write(gson.toJson(ApiResponse.error("该账号在其他地方登录")));
    }
}
