package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DisplayName("회원 데이터 테스트")
class MemberTest extends InitialTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 데이터 생성 테스트")
    void test_case_1() throws Exception {
        // given
        String userId = "yeji";
        Member member = Member.from(userId, "yeji", "yejicho");
        memberRepository.save(member);
        // when
        // then
        Member findMember = memberRepository.findById(userId).orElseThrow(() -> new IllegalStateException("일치하는 회원이 없습니다."));
        assertEquals(userId, findMember.getUserId());
    }

}