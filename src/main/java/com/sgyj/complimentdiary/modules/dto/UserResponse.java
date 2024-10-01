package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.User;
import lombok.Data;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 회원 정보 DTO
 */
@Data
public class UserResponse {

    private String userId;
    private String username;
    private String password;

    public static UserResponse from(User user) {
        UserResponse userResponse = new UserResponse();
        copyProperties(user, userResponse);
        return userResponse;
    }
}
