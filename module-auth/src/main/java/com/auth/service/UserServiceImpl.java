package com.auth.service;

import com.auth.dto.AuthedUserDto;
import com.auth.dto.UserDto;
import com.auth.entity.User;
import com.auth.entity.UserAuth;
import com.auth.repository.UserRepository;
import com.auth.service.impl.UserAuthService;
import com.auth.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAuthService userAuthService;

    @Override
    @Transactional
    public User findUser(AuthedUserDto auth) {
        UserAuth userAuth = userAuthService.findAuthById(auth.getAuthSeq());
        if (userAuth.getUser() == null) {
            //회복 시도
            return saveUser(userAuth.getUserEmail());
        }
        return userAuth.getUser();
    }

    @Override
    @Transactional
    public User updateUser(AuthedUserDto auth, UserDto.Request dto) {
        UserAuth userAuth = userAuthService.findAuthById(auth.getAuthSeq());
        if (userAuth.getUser() == null) {
            throw new IllegalArgumentException("사용자 데이터가 존재하지 않습니다");
        }

        User user = userAuth.getUser();

        user.changeUserName(dto.getUserName());//이름 변경
        user.changeUserEmail(dto.getUserEmail());//아매일 변경
        user.changeUserPhone(dto.getUserPhone());//휴대폰 번호 변경
        user.changeBirthday(dto.getBirthday());//생녀월일 변경
        user.changeGrade(dto.getGrade());//학년 변경
        user.changeCampus(dto.getCampus());//캠퍼스 변경
        user.changeStudentId(dto.getStudentId());//학번 변경
        user.changeSubject(dto.getSubject());//학과 변경
        return user;
    }

    private User saveUser(String userEmail) {
        //당장 넣을 수 있는 항목만 넣어서 반환
        try {
            User user = User.builder()
                    .userEmail(userEmail)//이메일 변경시 인증 필요
                    .userPhone(null)
                    .userName(null)
                    .birthday(null)
                    .campus(null)
                    .subject(null)
                    .studentId(null)
                    .grade(null)
                    .active(true)
                    .build();

            return userRepository.save(user);

        } catch (Exception e) {
            throw new IllegalArgumentException("사용자 데이터 저장중 에러 발생", e);
        }
    }
}
