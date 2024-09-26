package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.repository.DiaryRepository;
import com.sgyj.complimentdiary.modules.repository.FileRepository;
import com.sgyj.complimentdiary.modules.repository.UserDiaryRepository;
import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.File;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

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

        List<Diary> diaryList =
            createDiary.getDiaryContentList().stream().map(diaryEntries -> Diary.from(diaryEntries.getContent(),
                                                                                      diaryEntries.getRating())).toList();

        diaryRepository.saveAll(diaryList);

        if (!createDiary.getImageUrlList().isEmpty()) {
            List<File> fileList =
                createDiary.getImageUrlList().stream().map(imageUrl -> File.of(diaryList.get(0).getId(), imageUrl)).toList();
            fileRepository.saveAll(fileList);
        }

        for (Diary diary : diaryList) {
            UserDiary userDiary = UserDiary.from(createDiary.getUserId(), diary.getId());
            userDiaryRepository.save(userDiary);
        }

        return true;
    }

}
