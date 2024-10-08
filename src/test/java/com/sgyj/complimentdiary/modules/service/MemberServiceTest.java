package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.dto.CreateMemberRequest;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Transactional
@DisplayName("회원 테스트")
class MemberServiceTest extends InitialTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void password_encryption_test() {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setUserId("yeji");
        createMemberRequest.setUsername("yeji");
        createMemberRequest.setPassword("yejicho");

        memberService.createMember(createMemberRequest);
        Member member = memberRepository.findById("yeji").orElseThrow(IllegalArgumentException::new);
        Assertions.assertTrue(passwordEncoder.matches("yejicho", member.getPassword()));
    }

}