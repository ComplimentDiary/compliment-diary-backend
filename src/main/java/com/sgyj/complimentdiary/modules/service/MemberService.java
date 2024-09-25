package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.modules.dto.CreateMemberDto;
import com.sgyj.complimentdiary.modules.dto.MemberDto;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * 회원 생성 메소드
     *
     * @param memberDto
     * @return
     */
    public MemberDto createMember(CreateMemberDto memberDto) {
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());
        Member member = Member.from(memberDto.getUserId(), memberDto.getUsername(), encodePassword);
        memberRepository.save(member);
        return MemberDto.from(member);
    }

}
