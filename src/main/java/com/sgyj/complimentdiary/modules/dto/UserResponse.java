package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.Member;
import lombok.Data;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 회원 정보 DTO
 *
 * @author yeji.cho
 * @since 2024.10.02
 */
@Data
public class UserResponse {

    private String userId;
    private String username;
    private String password;

    public static UserResponse from(Member member) {
        UserResponse userResponse = new UserResponse();
        copyProperties(member, userResponse);
        return userResponse;
    }
}
