package com.cobweb.security.demo.security;

import com.cobweb.security.demo.entity.UserInfo;
import com.cobweb.security.demo.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 用户登录逻辑
 * @author: XRom
 * @createdTime: 2018-07-26 01:43:33
 */
@Service
@Slf4j
public class UserService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private UserInfo userInfo = null;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        userInfo = userInfoMapper.selectByLoginName(loginName);
        return buildUser(userInfo);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        userInfo = userInfoMapper.selectByPrimaryKey(userId);
        return buildUser(userInfo);
    }

    private SocialUserDetails buildUser(UserInfo userInfo) {
        if (userInfo == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return new SocialUser(userInfo.getLoginName(), userInfo.getLoginPwd(),true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
