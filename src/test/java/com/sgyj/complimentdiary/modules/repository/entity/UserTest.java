package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DisplayName("회원 데이터 테스트")
class UserTest extends InitialTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원 데이터 생성 테스트")
    void test_case_1() throws Exception {
        // given
        String userId = "yeji";
        User user = User.from(userId, "yeji", "yejicho", "yeji.cho@email.com");
        userRepository.save(user);
        // when
        // then
        User findUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("일치하는 회원이 없습니다."));
        assertEquals(userId, findUser.getUserId());
    }

}