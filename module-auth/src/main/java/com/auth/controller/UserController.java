package com.auth.controller;

import com.auth.dto.AuthedUserDto;
import com.auth.dto.UserDto;
import com.auth.entity.User;
import com.auth.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("auth/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto.Response userInfo(@AuthenticationPrincipal AuthedUserDto user) {
        User userInfo = userService.findUser(user);
        return UserDto.of(userInfo);
    }

    @PutMapping
    public UserDto.Response updateUserInfo(@AuthenticationPrincipal AuthedUserDto user, @RequestBody UserDto.Request dto) {
        User userInfo = userService.updateUser(user, dto);
        return UserDto.of(userInfo);
    }
}
