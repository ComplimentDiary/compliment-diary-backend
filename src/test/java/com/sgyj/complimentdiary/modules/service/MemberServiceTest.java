package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.dto.CreateUserRequest;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class MemberServiceTest extends InitialTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void password_encryption_test() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserId("yeji");
        createUserRequest.setUsername("yeji");
        createUserRequest.setPassword("yejicho");

        userService.createUser(createUserRequest);
        Member member = memberRepository.findById("yeji").orElseThrow(IllegalArgumentException::new);
        Assertions.assertTrue(passwordEncoder.matches("yejicho", member.getPassword()));
    }

}