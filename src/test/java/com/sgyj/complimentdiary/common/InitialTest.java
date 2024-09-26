package com.sgyj.complimentdiary.common;

import com.sgyj.complimentdiary.TestcontainersConfiguration;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Transactional
public class InitialTest {

}
