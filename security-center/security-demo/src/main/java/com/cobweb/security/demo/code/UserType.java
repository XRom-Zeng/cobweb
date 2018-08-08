package com.cobweb.security.demo.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: XRom
 * @createdTime: 2018-07-28 22:43:47
 */
@AllArgsConstructor
@Getter
public enum  UserType {

    NORMAL(0, "普通用户"),
    QQ(1, "普通用户"),
    WE_CHAT(2, "普通用户");

    private Integer code;

    private String name;

    public String getName(Integer code) {
        for (UserType userType : values()) {
            if (userType.getCode().equals(code)) {
                return userType.getName();
            }
        }
        return "类型错误";
    }

}
