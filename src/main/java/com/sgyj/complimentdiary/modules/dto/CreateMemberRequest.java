package com.sgyj.complimentdiary.modules.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 회원 생성 DTO
 *
 * @author yeji.cho
 * @since 2024.10.02
 */
@Data
public class CreateMemberRequest {

    @NotBlank(message = "사용자 아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "사용자 명을 입력해주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @Email(message = "올바르지 않은 이메일 형식입니다 : ${validatedValue}")
    private String email;
}
