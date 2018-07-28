package com.cobweb.security.demo.security;

import com.cobweb.security.demo.code.UserType;
import com.cobweb.security.demo.entity.UserInfo;
import com.cobweb.security.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author: XRom
 * @createdTime: 2018-07-26 18:51:58
 */
@Component
public class UserSignUp implements ConnectionSignUp {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public String execute(Connection<?> connection) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(connection.getDisplayName());
        userInfo.setUserState(0);
        if ("qq".equals(connection.getKey().getProviderId())) {
            userInfo.setUserState(UserType.QQ.getCode());
        }
        userInfo.setHeadUrl(connection.getImageUrl());
        userInfoMapper.insertSelective(userInfo);
        return userInfo.getUserId();
    }
}
