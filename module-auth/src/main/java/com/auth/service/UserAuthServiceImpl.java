package com.auth.service;

import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import com.auth.repository.UserAuthRepository;
import com.auth.service.impl.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserAuth saveAuth(UserAuthDto.Request dto) {
        if (userAuthRepository.existsByUserEmail(dto.getUserEmail())) {
            throw new IllegalArgumentException("이미 존재하는 아이디");
        }

        UserAuth userAuth = UserAuth.builder()
                .userEmail(dto.getUserEmail())
                .password(dto.getPassword())
                .active(false)
                .build();

        return userAuthRepository.save(userAuth);
    }

    @Override
    public UserAuth findAuth(UserAuthDto.Request dto) {
        UserAuth userAuth = userAuthRepository
                .findByUserEmailAndPasswordAndActive(dto.getUserEmail(), dto.getPassword(), true)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자를 찾을 수 없음"));
        return userAuth;
    }

    @Override
    public UserAuth updateAuth(UserAuthDto.Update dto) {
        UserAuth userAuth = userAuthRepository.findById(dto.getAuthSeq())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자를 찾을 수 없음"));
        userAuth.changeActive(false);// 기존 정보 soft delete

        //새로운 인증정보 생성
        UserAuth newAuth = UserAuth.builder()
                .userEmail(dto.getUserEmail())
                .password(dto.getPassword())
                .active(true)
                .build();
        return userAuthRepository.save(newAuth);
    }

    @Override
    public void deleteAuth(UserAuthDto.Delete dto) {
        userAuthRepository.deleteById(dto.getAuthSeq());
    }
}
