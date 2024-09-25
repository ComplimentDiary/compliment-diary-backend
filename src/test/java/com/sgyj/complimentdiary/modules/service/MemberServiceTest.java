package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.dto.CreateMemberDto;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class MemberServiceTest extends InitialTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void password_encryption_test() {
        CreateMemberDto createMemberDto = new CreateMemberDto();
        createMemberDto.setUserId("yeji");
        createMemberDto.setUsername("yeji");
        createMemberDto.setPassword("yejicho");

        memberService.createMember(createMemberDto);
        Member member = memberRepository.findById("yeji").orElseThrow(IllegalArgumentException::new);
        Assertions.assertTrue(passwordEncoder.matches("yejicho", member.getPassword()));
    }

}