package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.global.exception.ExceedContentException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryContentDto;
import com.sgyj.complimentdiary.modules.repository.DiaryMasterRepository;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.UserRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.User;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@DisplayName("일기 테스트")
class DiaryServiceTest extends InitialTest {

    @Autowired
    private DiaryMasterRepository diaryMasterRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryService diaryService;

    @Test
    @DisplayName("일기 등록 테스트")
    void test_case_1() throws Exception {
        // given
        User user = User.from("yeji", "yeji", "yeji", "yeji.cho@email.com");
        userRepository.save(user);
        // when
        String content = "칼퇴해서 집에왔다.";
        int rating = 4;
        Diary diaryEntry = Diary.from(content, rating);
        // then
        diaryRepository.save(diaryEntry);
        UserDiary userDiary = UserDiary.from("yeji", diaryEntry.getId(), List.of(diaryEntry));
        diaryMasterRepository.save(userDiary);

        UserDiary findUserDiary =
                diaryMasterRepository.findByUserIdAndDate("yeji", "2024-09-28").orElseThrow(() -> new IllegalStateException("일치하는 데이터가 없습니다."));

        assertEquals(1, findUserDiary.getDiaryList().size());
    }

    // TODO 테스트코드 수정 필요
    @Test
    @DisplayName("일기 내용 등록 테스트 - 최대 3개까지만 가능")
    void test_case_2() throws Exception {
        // given
        User user = User.from("yeji", "yeji", "yeji", "yeji.cho@email.com");
        userRepository.save(user);
        // when
        String content1 = "손을 씻었다.";
        String content2 = "자리를 양보해 드렸다.";
        String content3 = "공부를 했다.";
        int rating = 4;
        Diary diaryEntry1 = Diary.from(content1, rating);
        Diary diaryEntry2 = Diary.from(content2, rating);
        Diary diaryEntry3 = Diary.from(content3, rating);

        List<Diary> diaryList = List.of(diaryEntry1, diaryEntry2, diaryEntry3);
        diaryRepository.saveAll(diaryList);

        // UserDiary userDiary = UserDiary.from("yeji", , diaryList);
        // diaryMasterRepository.save(userDiary);
        UserDiary findUserDiary =
                diaryMasterRepository.findByUserIdAndDate("yeji", "2024-09-28").orElseThrow(() -> new IllegalStateException("일치하는 데이터가 없습니다."));
        // then
        assertEquals(3, findUserDiary.getDiaryList().size());
    }

    @Test
    @DisplayName("일기 칭찬글이 지정된 갯수를 넘어선 경우 에러 발생")
    void test_case_3() throws Exception {
        // given
        User user = User.from("yeji", "yeji", "yeji", "yeji.cho@email.com");
        userRepository.save(user);
        DiaryContentDto diaryContentDto = new DiaryContentDto();
        diaryContentDto.setContent("청소를 했다.");

        DiaryContentDto diaryContentDto1 = new DiaryContentDto();
        diaryContentDto1.setContent("양치를 했다.");

        DiaryContentDto diaryContentDto2 = new DiaryContentDto();
        diaryContentDto2.setContent("숙제를 했다.");

        DiaryContentDto diaryContentDto3 = new DiaryContentDto();
        diaryContentDto3.setContent("착한일을 했다.");
        // when
        CreateDiaryDto createDiary = new CreateDiaryDto();
        createDiary.setUserId("yeji");
        createDiary.setDate("2024-09-28");
        createDiary.setDiaryContentList(List.of(diaryContentDto, diaryContentDto2, diaryContentDto1, diaryContentDto3));
        // then

        assertThrows(ExceedContentException.class, () -> diaryService.createDiary(createDiary));

    }

}