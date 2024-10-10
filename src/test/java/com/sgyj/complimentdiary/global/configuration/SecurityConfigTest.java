package com.sgyj.complimentdiary.global.configuration;

import com.sgyj.complimentdiary.TestcontainersConfiguration;
import com.sgyj.complimentdiary.modules.dto.CreateMemberRequest;
import com.sgyj.complimentdiary.modules.repository.MemberRepository;
import com.sgyj.complimentdiary.modules.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("security 테스트")
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach  // JUnit 5의 @BeforeEach 사용
    public void init() {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setMemberId("yeji");
        createMemberRequest.setMemberId("yeji");
        createMemberRequest.setPassword("yeji");
        createMemberRequest.setEmail("yejicho@kakao.com");
        memberService.createMember(createMemberRequest);
    }

    @Test
    @DisplayName("로그인 테스트")
    public void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {

        MvcResult result = this.mockMvc.perform(post("/api/v1/member/login")
                        .param("loginId", "yeji")
                        .param("password", "yeji"))
                .andExpect(status().isOk())  // perform()의 결과로 andExpect를 호출
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mockMvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
