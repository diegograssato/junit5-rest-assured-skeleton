package com.automation.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Epic("Epic sample rest")
@Story("History about the rest service")
@DisplayName("History about the rest service")
public class OtherTestTest {


    @Description("Should calculate the correct sum")
    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @Tag("CSV")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5"
    })
    public void shouldHandlingSum(final int a, final int b, final int sum) {
        log.info("Sum parameter {}", sum);
        assertEquals(sum, a + b);
    }

    @Test
    @DisplayName("Should disable test using windows")
    @Description("Should disable test using windows")
    @Tag("my-tag")
    @DisabledOnOs(OS.WINDOWS)
    public void shouldHandlingDisableOnUseWindows(final TestInfo testInfo) {
        assertEquals("Should disable test using windows", testInfo.getDisplayName());
        log.info("Tag parameter {}", testInfo.getTags());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    @DisplayName("Should enable test using linux and mac")
    @Description("Should enable test using linux and mac")
    @Tag("my-tag-enable-linux-mac")
    @Link(value = "conditional-test-execution", url = "https://www.baeldung.com/junit-5-conditional-test-execution")
    @EnabledOnOs({OS.LINUX, OS.MAC})
    public void shouldHandlingEnableUsingLinuxAndMac(final TestInfo testInfo) {
        assertEquals("Should enable test using linux and mac", testInfo.getDisplayName());
        log.info("Tag parameter {}", testInfo.getTags());
        assertTrue(testInfo.getTags().contains("my-tag-enable-linux-mac"));
    }

    @Test
    @DisplayName("Should test is disabled")
    @Description("Should test is disabled")
    @Tag("my-tag-is-disabled")
    @Disabled
    public void shouldHandlingTestIsDisabled(final TestInfo testInfo) {
        assertEquals("Should enable test is disabled", testInfo.getDisplayName());
        log.info("Tag parameter {}", testInfo.getTags());
        assertTrue(testInfo.getTags().contains("my-tag-is-disabled"));
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @Description("Should RepeatedTest")
    @Tag("my-tag-repeated")
    @DisplayName("Repeat!")
    void shouldHandlingRepeat(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
        log.info("Tag parameter {}", testInfo.getTags());
        assertTrue(testInfo.getTags().contains("my-tag-repeated"));
    }

}