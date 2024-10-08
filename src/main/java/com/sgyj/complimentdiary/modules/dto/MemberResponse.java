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

    private String memberId;
    private String memberName;
    private String password;

    public static MemberResponse from(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        copyProperties(member, memberResponse);
        return memberResponse;
    }
}
