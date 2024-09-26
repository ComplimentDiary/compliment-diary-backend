package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exception.ExceedContentException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryResultDto;
import com.sgyj.complimentdiary.modules.repository.DiaryMasterRepository;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryMasterRepository diaryMasterRepository;

    private final DiaryRepository diaryRepository;

    public static final int MAX_CONTENT_COUNT = 3;

    /**
     * 일기 등록
     *
     * @param createDiary
     * @return
     */
    public DiaryResultDto createDiary(CreateDiaryDto createDiary) {

        Optional<UserDiary> optionalDiaryMaster =
                diaryMasterRepository.findByUserIdAndDate(createDiary.getUserId(), createDiary.getDate());

        if (optionalDiaryMaster.isPresent()) {
            List<Diary> diaryList = optionalDiaryMaster.get().getDiaryList();
            long textCount = diaryList.stream().filter(diaryEntry -> diaryEntry.getContent().equals(DiaryContentType.TEXT)).count();
            if (textCount >= MAX_CONTENT_COUNT) {
                throw new ExceedContentException("최대 등록 가능한 칭찬 글 수가 넘어갔습니다.");
            }
            long imageCount = diaryList.size() - textCount;
            if (imageCount >= MAX_CONTENT_COUNT) {
                throw new ExceedContentException("최대 등록 가능한 이미지 수가 넘어갔습니다.");
            }
        }

        List<Diary> diaryList =
                createDiary.getDiaryContentList().stream().map(diaryEntries -> Diary.from(diaryEntries.getContent(),
                        diaryEntries.getContentType(),
                        diaryEntries.getRating())).toList();

        UserDiary userDiary = UserDiary.from(createDiary.getUserId(), createDiary.getDate(), diaryList);
        diaryMasterRepository.save(userDiary);

        return DiaryResultDto.from(userDiary);
    }

}
