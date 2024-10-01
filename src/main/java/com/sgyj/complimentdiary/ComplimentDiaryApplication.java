package com.sgyj.complimentdiary;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class ComplimentDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplimentDiaryApplication.class, args);
    }

}
