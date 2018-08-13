package com.cobweb.admin.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author: XRom
 * @createdTime: 2018-08-13 10:23:23
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("callback/aliPay")
    public String aliPayCallback(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        request.getMethod();
        return "OK";
    }

    @RequestMapping("shell")
    public String shell() throws IOException, InterruptedException {
        String commandUrl = "/usr/local/temp/test.sh";
        Process ps = Runtime.getRuntime().exec(commandUrl);
        ps.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
