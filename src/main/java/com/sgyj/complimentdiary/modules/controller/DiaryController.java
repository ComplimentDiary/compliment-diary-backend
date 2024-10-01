package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateDiaryDto;
import com.sgyj.complimentdiary.modules.dto.DiaryContentResultDto;
import com.sgyj.complimentdiary.modules.dto.DiarySearchRequest;
import com.sgyj.complimentdiary.modules.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 다이어리 등록 기능
     *
     * @param createDiaryDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Boolean> registerDiary(CreateDiaryDto createDiaryDto) {
        return ResponseEntity.ok(diaryService.createDiary(createDiaryDto));
    }

    /**
     * 다이어로 조회 기능
     */
    @GetMapping
    public ResponseEntity<DiaryContentResultDto> retrieveSingleDiary(DiarySearchRequest diarySearchRequest) {
        return ResponseEntity.ok(diaryService.retrieveSingleDiary(diarySearchRequest));
    }

}
