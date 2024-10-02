package com.sgyj.complimentdiary;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableEncryptableProperties
@SpringBootApplication
public class ComplimentDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplimentDiaryApplication.class, args);
    }

}
