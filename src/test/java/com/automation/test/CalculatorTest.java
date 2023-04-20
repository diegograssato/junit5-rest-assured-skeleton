package com.automation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.automation.factory.Calculator;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
@Epic("Epic calculator")
@Story("Calculator test")
@DisplayName("Suite to handling multiplication should work")
class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    @Description("Simple multiplication should work with 20")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("blocker")
    void shouldPerformMultiplicationWorkTest() throws Exception {
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    @Description("Multiple with zero should be zero")
    @Severity(SeverityLevel.MINOR)
    void shouldHandlingMultipleWithZeroTest() throws Exception {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }

    @Test
    @DisplayName("Ensure handling Exception")
    @Description("Ensure handling IllegalArgumentException")
    @Severity(SeverityLevel.CRITICAL)
    void shouldHandlingExceptionTest() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> calculator.multiply(1000, 5));
    }


}