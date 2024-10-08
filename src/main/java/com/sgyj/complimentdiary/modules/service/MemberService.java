package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exceptions.NoMatchPasswordException;
import com.sgyj.complimentdiary.global.exceptions.NoMemberException;
import com.sgyj.complimentdiary.modules.dto.CreateMemberRequest;
import com.sgyj.complimentdiary.modules.dto.MemberResponse;
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
     * @param createMemberRequest
     * @return
     */
    public MemberResponse createMember(CreateMemberRequest createMemberRequest) {
        String encodePassword = passwordEncoder.encode(createMemberRequest.getPassword());
        Member member = Member.of(createMemberRequest.getUserId(), createMemberRequest.getUsername(), encodePassword, createMemberRequest.getEmail());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    /**
     * 로그인 메소드
     *
     * @param loginId
     * @param password
     * @return
     */
    public MemberResponse login(String loginId, String password) {
        Member member = memberRepository.findByMemberId(loginId).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(member.getPassword(), password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.from(member);
    }

}
