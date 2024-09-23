package com.sgyj.complimentdiary;

import org.springframework.boot.SpringApplication;

public class TestComplimentDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.from(ComplimentDiaryApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
