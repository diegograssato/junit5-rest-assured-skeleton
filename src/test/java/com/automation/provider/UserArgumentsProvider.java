package com.automation.provider;

import com.automation.factory.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        final Faker faker = new Faker();
        return Stream.generate(() -> Arguments.of(User.builder().name(faker.name().firstName().replaceAll("\\s","").replaceAll("\\W","").trim().toLowerCase()).build())).limit(3);


    }
}
