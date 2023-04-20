package com.automation.provider;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PokemonProvider {

    public static Stream<Arguments> getPokemons() {

        final Faker faker = new Faker();


        return Stream.of(

                Arguments.of(faker.name().firstName()),
                  Arguments.of(faker.name().firstName())
        );
    }
}
