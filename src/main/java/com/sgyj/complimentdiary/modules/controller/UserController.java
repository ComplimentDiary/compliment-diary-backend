package com.sgyj.complimentdiary.modules.controller;

import com.sgyj.complimentdiary.modules.dto.CreateUserRequest;
import com.sgyj.complimentdiary.modules.dto.UserResponse;
import com.sgyj.complimentdiary.modules.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    /**
     * 로그인
     *
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(String loginId, String password) {
        return ResponseEntity.ok(userService.login(loginId, password));
    }

}
