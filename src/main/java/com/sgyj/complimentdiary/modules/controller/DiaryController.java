package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateDiaryRequest;
import com.sgyj.complimentdiary.modules.dto.DiaryResultDto;
import com.sgyj.complimentdiary.modules.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param createDiaryRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<DiaryResultDto> registerDiary(@RequestBody @Valid CreateDiaryRequest createDiaryRequest) {
        return ResponseEntity.ok(diaryService.createDiary(createDiaryRequest));
    }

}
