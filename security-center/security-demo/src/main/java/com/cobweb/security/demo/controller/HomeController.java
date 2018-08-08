package com.cobweb.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XRom
 * @createdTime: 2018-07-27 01:29:36
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    @ResponseBody
    public String index(HttpServletRequest request) {
        return "hello world";
    }

    @GetMapping("login")
    public String login() {
        return "user-login";
    }
}
