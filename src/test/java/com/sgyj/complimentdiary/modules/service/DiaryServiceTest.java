package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.global.exceptions.ExceedContentException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryRequest;
import com.sgyj.complimentdiary.modules.dto.DiaryContent;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.MemberDiaryRepository;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import com.sgyj.complimentdiary.modules.repository.entity.MemberDiary;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ActiveProfiles("test")
@Transactional
@DisplayName("일기 테스트")
class DiaryServiceTest extends InitialTest {

    @Autowired
    private MemberDiaryRepository memberDiaryRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DiaryService diaryService;

    @BeforeEach
    public void init() {
        Member member = Member.of("yeji", "yeji", "yeji", "yeji.cho@email.com");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("일기 등록 테스트")
    void test_case_1() throws Exception {
        // given
        Member member = memberRepository.findByMemberId("yeji").orElseThrow(() -> new IllegalStateException("일치하는 회원이 없습니다."));

        // when
        MemberDiary memberDiary = MemberDiary.from(member, "2024-09-28");

        String content = "칼퇴해서 집에왔다.";
        int rating = 4;
        Diary diaryEntry = Diary.from(content, rating, memberDiary);
        // then
        memberDiary.getDiaryList().add(diaryEntry);
        memberDiaryRepository.save(memberDiary);
        diaryRepository.save(diaryEntry);

        assertEquals(1, memberDiary.getDiaryList().size());
    }

    @Test
    @DisplayName("일기 내용 등록 테스트 - 최대 3개까지만 가능")
    void test_case_2() throws Exception {
        // given
        Member member = memberRepository.findByMemberId("yeji").orElseThrow(() -> new IllegalStateException("일치하는 회원이 없습니다."));

        MemberDiary memberDiary = MemberDiary.from(member, "2024-09-28");

        // when
        String content1 = "손을 씻었다.";
        String content2 = "자리를 양보해 드렸다.";
        String content3 = "공부를 했다.";
        int rating = 4;
        Diary diaryEntry1 = Diary.from(content1, rating, memberDiary);
        Diary diaryEntry2 = Diary.from(content2, rating, memberDiary);
        Diary diaryEntry3 = Diary.from(content3, rating, memberDiary);

        List<Diary> diaryList = List.of(diaryEntry1, diaryEntry2, diaryEntry3);
        memberDiary.getDiaryList().addAll(diaryList);
        diaryRepository.saveAll(diaryList);
        memberDiaryRepository.save(memberDiary);

        // then
        assertEquals(3, memberDiary.getDiaryList().size());
    }

    @Test
    @DisplayName("일기 칭찬글이 지정된 갯수를 넘어선 경우 에러 발생")
    void test_case_3() throws Exception {
        // given

        DiaryContent diaryContent = new DiaryContent();
        diaryContent.setContent("청소를 했다.");

        DiaryContent diaryContent1 = new DiaryContent();
        diaryContent1.setContent("양치를 했다.");

        DiaryContent diaryContent2 = new DiaryContent();
        diaryContent2.setContent("숙제를 했다.");

        DiaryContent diaryContent3 = new DiaryContent();
        diaryContent3.setContent("착한일을 했다.");
        // when
        CreateDiaryRequest createDiary = new CreateDiaryRequest();
        createDiary.setMemberId("yeji");
        createDiary.setDate("2024-09-28");
        createDiary.setDiaryContentList(List.of(diaryContent, diaryContent2, diaryContent1, diaryContent3));
        // then

        assertThrows(ExceedContentException.class, () -> diaryService.createDiary(createDiary));

    }

}