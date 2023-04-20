package com.automation.test;

import com.automation.extension.RestExtension;
import com.automation.definition.ValidationDefinitions;
import com.automation.factory.User;
import com.automation.provider.UserArgumentsProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

@Slf4j
@Epic("Epic using the Parameterized Test")
@DisplayName("Parameterized support for external providers")
public class ParameterizedRestTest extends ValidationDefinitions {

    @RegisterExtension
    static RestExtension restExtension = RestExtension.builder()
            .baseUri(configuration.jsontestPath().toString())
            .build();


    @ParameterizedTest
    @MethodSource("com.automation.provider.PokemonProvider#getPokemons")
    @Story("Testing the pokemon has been received")
    @Description("Testing the pokemon has been received using ParameterizedTest")
    @DisplayName("Testing the pokemon has been received")
    public void shouldPerformTOParameterizedGETPokemons(final String pokemon) throws Exception {

        // Given
        final String url = "/" + pokemon +
                "/" + pokemon;

        // When
        log.info("Requesting GET: {}", url);
        response = restExtension.useSpec().get(url);


        // Then
        this.verifyResponseStatusValue(200);
        this.verifyResponseKeyValue(pokemon, pokemon);

    }

    @ParameterizedTest
    @MethodSource("com.automation.provider.UserProvider#getUsers")
    @Story("Testing the user has been received")
    @Description("Testing the user has been received using ParameterizedTest")
    @DisplayName("Testing the user has been received")
    @Tag("regression")
    public void shouldPerformTOParameterizedGETUsers(final User user) throws Exception {

        // Given
        final String url = "/".concat(user.getName()).concat("/").concat(user.getName());

        // When
        log.info("Requesting GET: {}", url);
        response = restExtension.useSpec().get(url);

        // Then
        this.verifyResponseStatusValue(200);
        this.verifyResponseKeyValue(user.getName(), user.getName());


    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    @Story("Testing the user has been received")
    @Description("Testing the user has been received using ArgumentsSource")
    @DisplayName("Testing the user has been received")
    @Tag("regression")
    void shouldPerformTOArgumentsSourceGETUsers(final User user) throws Exception {
        // Given
        final String url = "/".concat(user.getName()).concat("/").concat(user.getName());

        // When
        log.info("Requesting GET: {}", url);
        response = restExtension.useSpec().get(url);

        // Then
        this.verifyResponseStatusValue(200);
        this.verifyResponseKeyValue(user.getName(), user.getName());
    }
}