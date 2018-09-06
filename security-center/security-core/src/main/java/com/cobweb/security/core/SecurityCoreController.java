package com.cobweb.security.core;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-03 00:06:23
 */
@RestController
@RequestMapping("security")
public class SecurityCoreController {

    @GetMapping("me")
    public Authentication me(Authentication authentication) {
        return authentication;
    }
}
