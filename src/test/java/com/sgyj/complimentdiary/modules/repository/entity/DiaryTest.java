package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.MemberDiaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("일기 테스트")
class DiaryTest extends InitialTest {

    @Autowired
    private MemberDiaryRepository memberDiaryRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    @DisplayName("일기 생성 테스트")
    void test_case_1() throws Exception {
        // given
        String date = "2024-09-23";
        String memberId = "yeji";
        String content1 = "나 오늘 책상에 앉았어.";
        // when

        // then

    }

}
