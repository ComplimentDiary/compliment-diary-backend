package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exceptions.ExceedContentException;
import com.sgyj.complimentdiary.global.exceptions.NoMemberException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryRequest;
import com.sgyj.complimentdiary.modules.dto.DiaryResultResponse;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.FileRepository;
import com.sgyj.complimentdiary.modules.repository.MemberDiaryRepository;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.File;
import com.sgyj.complimentdiary.modules.repository.entity.Member;
import com.sgyj.complimentdiary.modules.repository.entity.MemberDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final MemberRepository memberRepository;

    private final MemberDiaryRepository memberDiaryRepository;

    private final DiaryRepository diaryRepository;

    private final FileRepository fileRepository;

    public static final int MAX_CONTENT_COUNT = 3;

    /**
     * 일기 등록
     * 일기 글은 최대 3개, 이미지 또한 최대 3개까지만 등록 가능함.
     * 해당 값 설정은 추후 변경될 수 있음. (구독시스템에 의해 관리 예정)
     *
     * @param createDiary
     * @return
     */
    public DiaryResultResponse createDiary(CreateDiaryRequest createDiary) {

        Member member = memberRepository.findByMemberId(createDiary.getMemberId()).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        MemberDiary memberDiary = memberDiaryRepository.findByMemberAndDiaryDate(member, createDiary.getDate()).orElse(MemberDiary.from(member, createDiary.getDate()));

        List<Diary> diaryList =
                createDiary.getDiaryContentList().stream().map(diaryEntries -> Diary.from(diaryEntries.getContent(),
                        diaryEntries.getRating(),
                        memberDiary)).toList();

        if (memberDiary.getDiaryList().size() + diaryList.size() > MAX_CONTENT_COUNT) {
            throw new ExceedContentException("등록 가능한 콘텐츠 수를 벗어났습니다.");
        }

        memberDiary.getDiaryList().addAll(diaryList);
        memberDiaryRepository.save(memberDiary);
        diaryRepository.saveAll(diaryList);

        if (!createDiary.getImageUrlList().isEmpty()) {
            List<File> fileList =
                    createDiary.getImageUrlList().stream().map(imageUrl -> File.of(memberDiary.getId(), imageUrl)).toList();
            fileRepository.saveAll(fileList);
        }

        return DiaryResultResponse.from(memberDiary);
    }

}
