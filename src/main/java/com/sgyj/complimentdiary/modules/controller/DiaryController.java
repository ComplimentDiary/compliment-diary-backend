package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryResultDto;
import com.sgyj.complimentdiary.modules.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/register")
    public ResponseEntity<DiaryResultDto> registerDiary(CreateDiaryDto createDiaryDto) {
        return ResponseEntity.ok(diaryService.createDiary(createDiaryDto));
    }

}
