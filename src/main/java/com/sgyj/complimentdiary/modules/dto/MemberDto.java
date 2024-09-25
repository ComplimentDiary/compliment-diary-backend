package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.Member;
import lombok.Data;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 회원 정보 DTO
 */
@Data
public class MemberDto {

    private String userId;
    private String username;
    private String password;

    public static MemberDto from(Member member) {
        MemberDto memberDto = new MemberDto();
        copyProperties(member, memberDto);
        return memberDto;
    }
}
