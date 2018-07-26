package com.cobweb.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XRom
 * @createdTime: 2018-07-27 01:29:36
 */
@Controller
public class HomeController {

    @GetMapping
    @ResponseBody
    public String index(HttpServletRequest request) {
        return "hello world";
    }

    @GetMapping("home/login")
    public String login() {
        return "user-login";
    }
}
