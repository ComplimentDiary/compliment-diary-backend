package com.sgyj.complimentdiary.infra;

import com.sgyj.complimentdiary.common.InitialTest;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@EnableEncryptableProperties
@Slf4j
class JasyptConfigTest extends InitialTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    @DisplayName("jasypt 테스트")
    void test_case_1() throws Exception {
        // given
        // when
        String test = "jasypt";
        // then
        String encrypt = stringEncryptor.encrypt(test);
        log.info("Encrypt :: {} ", encrypt);
        Assertions.assertEquals(test, stringEncryptor.decrypt(encrypt));
    }

}
