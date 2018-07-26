package com.cobweb.security.demo.mapper;

import com.cobweb.security.demo.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据登录名查找用户
     * @param loginName 登录名
     * @return
     */
    UserInfo selectByLoginName(String loginName);
}