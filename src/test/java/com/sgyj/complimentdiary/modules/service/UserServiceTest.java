package com.sgyj.complimentdiary.modules.service;

import com.sgyj.complimentdiary.common.InitialTest;
import com.sgyj.complimentdiary.modules.dto.CreateUserRequest;
import com.sgyj.complimentdiary.modules.repository.UserRepository;
import com.sgyj.complimentdiary.modules.repository.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest extends InitialTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void password_encryption_test() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserId("yeji");
        createUserRequest.setUsername("yeji");
        createUserRequest.setPassword("yejicho");

        userService.createMember(createUserRequest);
        User user = userRepository.findById("yeji").orElseThrow(IllegalArgumentException::new);
        Assertions.assertTrue(passwordEncoder.matches("yejicho", user.getPassword()));
    }

}