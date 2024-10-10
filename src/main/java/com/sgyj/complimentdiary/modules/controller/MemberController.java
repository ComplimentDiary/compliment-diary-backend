package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateMemberRequest;
import com.sgyj.complimentdiary.modules.dto.MemberResponse;
import com.sgyj.complimentdiary.modules.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid CreateMemberRequest request) {
        return ResponseEntity.ok(memberService.createMember(request));
    }

    /**
     * 로그인
     *
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestParam String loginId, @RequestParam String password) {
        return ResponseEntity.ok(memberService.login(loginId, password));
    }

}
