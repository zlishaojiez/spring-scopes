package cn.shaojiel.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class LogbackMaskingTest {

    @Test
    @DisplayName("Password masking in log")
    void passwordMaskingInLog() {
        String password = "{\"password\": \"1234\"}";
        log.info("{}", password);
    }
}
