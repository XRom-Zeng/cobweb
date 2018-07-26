package com.cobweb.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: XRom
 * @createdTime: 2018-07-27 01:29:36
 */
@RequestMapping("home")
@Controller
public class HomeController {

    @GetMapping("login")
    public String login() {
        return "user-login";
    }
}
