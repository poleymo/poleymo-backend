package com.auth.service.impl;

import com.auth.dto.AuthedUserDto;
import com.auth.dto.UserDto;
import com.auth.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface UserService {
    User findUser(@AuthenticationPrincipal AuthedUserDto auth);
    User updateUser(@AuthenticationPrincipal AuthedUserDto auth, UserDto.Request dto);
}
