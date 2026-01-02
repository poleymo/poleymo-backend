package com.auth.service.impl;

import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;

public interface UserAuthService {
    UserAuth saveAuth(UserAuthDto.Request dto);
    UserAuth findAuth(UserAuthDto.Request dto);
    UserAuth updateAuth(UserAuthDto.Update dto);
    void deleteAuth(UserAuthDto.Delete dto);
}
