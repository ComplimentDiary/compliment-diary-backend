package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exceptions.NoMatchPasswordException;
import com.sgyj.complimentdiary.global.exceptions.NoMemberException;
import com.sgyj.complimentdiary.modules.dto.CreateUserRequest;
import com.sgyj.complimentdiary.modules.dto.UserResponse;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 생성 메소드
     *
     * @param createUserRequest
     * @return
     */
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        String encodePassword = passwordEncoder.encode(createUserRequest.getPassword());
        Member member = Member.from(createUserRequest.getUserId(), createUserRequest.getUsername(), encodePassword, createUserRequest.getEmail());
        memberRepository.save(member);
        return UserResponse.from(member);
    }

    /**
     * 로그인 메소드
     *
     * @param loginId
     * @param password
     * @return
     */
    public UserResponse login(String loginId, String password) {
        Member member = memberRepository.findById(loginId).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(member.getPassword(), password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return UserResponse.from(member);
    }

}
