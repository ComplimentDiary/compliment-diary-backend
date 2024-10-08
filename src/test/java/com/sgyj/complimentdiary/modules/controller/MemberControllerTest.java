package com.sgyj.complimentdiary.modules.controller;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import com.sgyj.complimentdiary.TestcontainersConfiguration;
import com.sgyj.complimentdiary.modules.dto.CreateMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@DisplayName("사용자 api 호출 테스트")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 성공 테스트")
    void sign_up_success() throws Exception {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setUserId("yejiCho");
        createMemberRequest.setUsername("조예지");
        createMemberRequest.setPassword("yejiCho");
        createMemberRequest.setEmail("yejiCho@email.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/signup")
                .content(objectMapper.writeValueAsString(createMemberRequest))
                .contentType(ContentType.APPLICATION_JSON.toString())).andExpect(status().isOk());
    }

}