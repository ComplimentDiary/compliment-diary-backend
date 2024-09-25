package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exception.NoMatchPasswordException;
import com.sgyj.complimentdiary.global.exception.NoMemberException;
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

    /**
     * 로그인 메소드
     *
     * @param loginId
     * @param password
     * @return
     */
    public MemberDto login(String loginId, String password) {
        Member member = memberRepository.findById(loginId).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(member.getPassword(), password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return MemberDto.from(member);
    }

}
