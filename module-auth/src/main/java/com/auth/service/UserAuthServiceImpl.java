package com.auth.service;

import com.auth.dto.AuthedUserDto;
import com.auth.dto.CustomAuthDetails;
import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import com.auth.repository.UserAuthRepository;
import com.auth.service.impl.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncryptor passwordEncryptor;

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
    public UserAuth findAuthById(Long id) {
        return userAuthRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자를 찾을 수 없음")) ;
    }

    @Override
    public void deleteAuth(UserAuthDto.Delete dto) {
        userAuthRepository.deleteById(dto.getAuthSeq());
    }

    @Override
    public UserAuth changePassword(AuthedUserDto user, UserAuthDto.Update dto) {
        UserAuth auth = findAuthById(dto.getAuthSeq());
        String password = passwordEncryptor.encrypt(dto.getPassword());
        String newPassword = passwordEncryptor.encrypt(dto.getNewPassword());

        if (!Objects.equals(password, auth.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다름");
        }

        //요청한 사용자 아이디와 토큰의 사용자 아이디가 다르면 예외
        if (Objects.equals(dto.getAuthSeq(), auth.getAuthSeq())) {
            auth.changeActive(false);
            UserAuth newAuth =
                    UserAuth.builder()
                            .userEmail(user.getUserEmail())
                            .password(newPassword)
                            .active(true).build();
            userAuthRepository.save(newAuth);
            return newAuth;
        }
        throw new IllegalArgumentException("잘못된 접근입니다.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuth> userAuthO = userAuthRepository.findByUserEmail(username);
        UserAuth userAuth = userAuthO.get();

        CustomAuthDetails customAuthDetails = new CustomAuthDetails(
                userAuth.getAuthSeq(),
                userAuth.getUserEmail(),
                userAuth.getPassword(),
                "ROLE_USER"
        );

        return customAuthDetails;
    }
}
