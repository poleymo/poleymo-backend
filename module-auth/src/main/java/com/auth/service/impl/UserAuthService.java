package com.auth.service.impl;

import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService extends UserDetailsService {
    UserAuth saveAuth(UserAuthDto.Request dto);
    UserAuth findAuth(UserAuthDto.Request dto);
    UserAuth findAuthById(Long id);
    UserAuth updateAuth(UserAuthDto.Update dto);
    void deleteAuth(UserAuthDto.Delete dto);
}
