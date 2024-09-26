package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exception.NoMatchPasswordException;
import com.sgyj.complimentdiary.global.exception.NoMemberException;
import com.sgyj.complimentdiary.modules.dto.CreateUserRequest;
import com.sgyj.complimentdiary.modules.dto.UserResponse;
import com.sgyj.complimentdiary.modules.repository.UserRepository;
import com.sgyj.complimentdiary.modules.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 생성 메소드
     *
     * @param memberDto
     * @return
     */
    public UserResponse createMember(CreateUserRequest memberDto) {
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());
        User user = User.from(memberDto.getUserId(), memberDto.getUsername(), encodePassword, memberDto.getEmail());
        userRepository.save(user);
        return UserResponse.from(user);
    }

    /**
     * 로그인 메소드
     *
     * @param loginId
     * @param password
     * @return
     */
    public UserResponse login(String loginId, String password) {
        User user = userRepository.findById(loginId).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(user.getPassword(), password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return UserResponse.from(user);
    }

}
