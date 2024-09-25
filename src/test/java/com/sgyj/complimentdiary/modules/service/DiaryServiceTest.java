package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.global.exception.ExceedContentException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryContentDto;
import com.sgyj.complimentdiary.modules.repository.DiaryMasterRepository;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.DiaryEntries;
import com.sgyj.complimentdiary.modules.repository.entity.DiaryMaster;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
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
    private MemberRepository memberRepository;

    @Autowired
    private DiaryService diaryService;

    @Test
    @DisplayName("일기 등록 테스트")
    void test_case_1() throws Exception {
        // given
        Member member = Member.from("yeji", "yeji", "yeji");
        memberRepository.save(member);
        // when
        String content = "칼퇴해서 집에왔다.";
        int rating = 4;
        DiaryEntries diaryEntry = DiaryEntries.from(content, DiaryContentType.TEXT, rating);
        // then
        diaryRepository.save(diaryEntry);
        DiaryMaster diaryMaster = DiaryMaster.from("yeji", "2024-09-28", List.of(diaryEntry));
        diaryMasterRepository.save(diaryMaster);

        DiaryMaster findDiaryMaster =
            diaryMasterRepository.findByUserIdAndDate("yeji", "2024-09-28").orElseThrow(() -> new IllegalStateException("일치하는 데이터가 없습니다."));

        assertEquals(1, findDiaryMaster.getDiaryEntriesList().size());
    }

    @Test
    @DisplayName("일기 내용 등록 테스트 - 최대 3개까지만 가능")
    void test_case_2() throws Exception {
        // given
        Member member = Member.from("yeji", "yeji", "yeji");
        memberRepository.save(member);
        // when
        String content1 = "손을 씻었다.";
        String content2 = "자리를 양보해 드렸다.";
        String content3 = "공부를 했다.";
        int rating = 4;
        DiaryEntries diaryEntry1 = DiaryEntries.from(content1, DiaryContentType.TEXT, rating);
        DiaryEntries diaryEntry2 = DiaryEntries.from(content2, DiaryContentType.TEXT, rating);
        DiaryEntries diaryEntry3 = DiaryEntries.from(content3, DiaryContentType.TEXT, rating);

        List<DiaryEntries> diaryEntriesList = List.of(diaryEntry1, diaryEntry2, diaryEntry3);
        diaryRepository.saveAll(diaryEntriesList);

        DiaryMaster diaryMaster = DiaryMaster.from("yeji", "2024-09-28", diaryEntriesList);
        diaryMasterRepository.save(diaryMaster);
        DiaryMaster findDiaryMaster =
            diaryMasterRepository.findByUserIdAndDate("yeji", "2024-09-28").orElseThrow(() -> new IllegalStateException("일치하는 데이터가 없습니다."));
        // then
        assertEquals(3, findDiaryMaster.getDiaryEntriesList().size());
    }

    @Test
    @DisplayName("일기 칭찬글이 지정된 갯수를 넘어선 경우 에러 발생")
    void test_case_3() throws Exception {
        // given
        Member member = Member.from("yeji", "yeji", "yeji");
        memberRepository.save(member);
        DiaryContentDto diaryContentDto = new DiaryContentDto();
        diaryContentDto.setContentType(DiaryContentType.TEXT);
        diaryContentDto.setContent("청소를 했다.");

        DiaryContentDto diaryContentDto1 = new DiaryContentDto();
        diaryContentDto1.setContentType(DiaryContentType.TEXT);
        diaryContentDto1.setContent("양치를 했다.");

        DiaryContentDto diaryContentDto2 = new DiaryContentDto();
        diaryContentDto2.setContentType(DiaryContentType.TEXT);
        diaryContentDto2.setContent("숙제를 했다.");

        DiaryContentDto diaryContentDto3 = new DiaryContentDto();
        diaryContentDto3.setContentType(DiaryContentType.TEXT);
        diaryContentDto3.setContent("착한일을 했다.");
        // when
        CreateDiaryDto createDiary = new CreateDiaryDto();
        createDiary.setUserId("yeji");
        createDiary.setDate("2024-09-28");
        createDiary.setDiaryContentList(List.of(diaryContentDto, diaryContentDto2, diaryContentDto1, diaryContentDto3));
        // then

        assertThrows(ExceedContentException.class, () -> {
            diaryService.createDiary(createDiary);
        });

    }

}