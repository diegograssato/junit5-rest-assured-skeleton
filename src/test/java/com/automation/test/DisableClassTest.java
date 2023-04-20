package com.automation.test;

import com.automation.factory.Calculator;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Epic("Epic has disabled")
@Story("History has disabled")
@DisplayName("History has disabled")
@Disabled
public class DisableClassTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should Disabled")
    @Description("Simple multiplication should Disabled")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("blocker")
    void shouldPerformMultiplicationDisabledTest() throws Exception {
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should Disabled");
    }

}