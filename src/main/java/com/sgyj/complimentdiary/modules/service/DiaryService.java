package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.global.exceptions.ExceedContentException;
import com.sgyj.complimentdiary.global.exceptions.NoMemberException;
import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.FileRepository;
import com.sgyj.complimentdiary.modules.repository.UserDiaryRepository;
import com.sgyj.complimentdiary.modules.repository.UserRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.File;
import com.sgyj.complimentdiary.modules.repository.entity.User;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final UserRepository userRepository;

    private final UserDiaryRepository userDiaryRepository;

    private final DiaryRepository diaryRepository;

    private final FileRepository fileRepository;

    public static final int MAX_CONTENT_COUNT = 3;

    /**
     * 일기 등록
     *
     * @param createDiary
     * @return
     */
    public boolean createDiary(CreateDiaryDto createDiary) {

        User user = userRepository.findById(createDiary.getUserId()).orElseThrow(() -> new NoMemberException("일치하는 회원을 찾을 수 없습니다."));

        UserDiary userDiary = userDiaryRepository.findByUserAndDiaryDate(user, createDiary.getDate()).orElse(UserDiary.from(user, createDiary.getDate()));

        List<Diary> diaryList =
                createDiary.getDiaryContentList().stream().map(diaryEntries -> Diary.from(diaryEntries.getContent(),
                        diaryEntries.getRating(),
                        userDiary)).toList();

        if (userDiary.getDiaryList().size() + diaryList.size() > MAX_CONTENT_COUNT) {
            throw new ExceedContentException("등록 가능한 콘텐츠 수를 벗어났습니다.");
        }

        userDiary.getDiaryList().addAll(diaryList);
        userDiaryRepository.save(userDiary);
        diaryRepository.saveAll(diaryList);

        if (!createDiary.getImageUrlList().isEmpty()) {
            List<File> fileList =
                    createDiary.getImageUrlList().stream().map(imageUrl -> File.of(userDiary.getId(), imageUrl)).toList();
            fileRepository.saveAll(fileList);
        }

        return true;
    }

}
