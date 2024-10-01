package com.sgyj.complimentdiary.modules.dto;

import lombok.Data;

/**
 * 회원 생성 DTO
 */
@Data
public class CreateUserRequest {

    private String userId;
    private String username;
    private String password;
    private String email;
}
