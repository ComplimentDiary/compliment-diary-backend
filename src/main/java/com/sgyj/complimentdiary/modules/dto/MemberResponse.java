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
public class MemberResponse {

    private Long id;
    private String memberId;
    private String memberName;
    private String email;
    private String password;
    private RoleType role;
    private String accessToken;

    public static MemberResponse from(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        copyProperties(member, memberResponse, "password");
        return memberResponse;
    }

    public static MemberResponse of(Member member, String accessToken) {
        MemberResponse memberResponse = new MemberResponse();
        copyProperties(member, memberResponse, "password");
        return memberResponse;
    }
}
