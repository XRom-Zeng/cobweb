package com.cobweb.security.demo.controller;

import com.cobweb.security.demo.entity.UserInfo;
import com.cobweb.security.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping
    public String test() {
        return "hello spring security";
    }

    @GetMapping("user")
    public UserInfo user() {
        int a = 2/0;
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName("XRom");
        userInfo.setLoginPwd(new BCryptPasswordEncoder().encode("XRom45"));
        userInfo.setUserName("XRom");
        userInfo.setUserState(0);
        userInfo.setUserSex(0);
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }
}
