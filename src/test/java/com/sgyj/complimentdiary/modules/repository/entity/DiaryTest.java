package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.repository.DiaryMasterRepository;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("일기 테스트")
class DiaryTest extends InitialTest {

    @Autowired
    private DiaryMasterRepository diaryMasterRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    @DisplayName("일기 생성 테스트")
    void test_case_1() throws Exception {
        // given
        String date = "2024-09-23";
        String userId = "yeji";
        String content1 = "나 오늘 책상에 앉았어.";
        // when

        // then

    }

}
