package com.automation.test;

import com.automation.extension.RestExtension;
import com.automation.definition.ValidationDefinitions;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Slf4j
@Epic("Epic about the CEP Service sample history")
@Story("History about the cep service")
@DisplayName("History about the cep service")
public class CEPServicesTest extends ValidationDefinitions {

    @RegisterExtension
    static RestExtension restExtension = RestExtension.builder()
            .baseUri(configuration.cepPath().toString())
            .build();

    private static Stream<Arguments> getCeps() {

        return Stream.of(
                Arguments.of("12246870"),
                Arguments.of("12246873")
             //    Arguments.of("15047400")
        );
    }

    @ParameterizedTest
    @MethodSource("getCeps")
    @Description("Testing the CEP has been received")
    @DisplayName("Testing the CEP has been received")
    @Order(2)
    @Tag("Order_2")
    @Tag("production")
    @Issue("DOPS-419")
    @Link(name="DOPS-419", url = "https://jira.com.br/browse/DOPS-419")
    public void shouldPerformTOParameterizedCEPTest(final String cep) throws Exception {

        final String url = "/ws/" + cep + "/json";

        log.info("Requesting GET: {}", url);
        response = restExtension.useSpec().get(url);


        this.verifyResponseStatusValue(200);

    }

    @ParameterizedTest
    @MethodSource("getCeps")
    @Order(1)
    @Tag("Order_1")
    @Tag("debug")
    @Description("Testing the CEP has validate schema")
    @DisplayName("Testing the CEP has validate schema#")
    public void shouldPerformTOValidateSchemaParameterizedCEPTest(final String cep) throws Exception {

        final String url = "/ws/" + cep + "/json";

        log.info("Requesting GET: {}", url);
        // Exemplo de como debug o metodo when().log().all() ou when().log().ifValidationFails()
        response = restExtension.useSpec().when().log().ifValidationFails().get(url);

        this.validationSchema(cep);

    }


}