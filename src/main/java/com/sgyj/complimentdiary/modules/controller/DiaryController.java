package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryResultDto;
import com.sgyj.complimentdiary.modules.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 일기 등록
     *
     * @param createDiaryDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<DiaryResultDto> registerDiary(CreateDiaryDto createDiaryDto) {
        return ResponseEntity.ok(diaryService.createDiary(createDiaryDto));
    }

}
