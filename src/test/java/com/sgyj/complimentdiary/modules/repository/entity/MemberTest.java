package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("회원 데이터 테스트")
class MemberTest extends InitialTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 데이터 생성 테스트")
    void test_case_1() throws Exception {
        // given
        String memberId = "yeji";
        Member member = Member.of(memberId, "yeji", "yejicho", "yeji.cho@email.com");
        memberRepository.save(member);
        // when
        // then
        Member findMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalStateException("일치하는 회원이 없습니다."));
        assertEquals(memberId, findMember.getMemberId());
    }

}