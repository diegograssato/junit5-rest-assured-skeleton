package com.automation.provider;

import com.github.javafaker.Faker;
import com.automation.factory.User;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class UserProvider {
    public static Stream<Arguments> getUsers() {

       return Stream.generate(() -> Arguments.of(User.builder().name(getUsername().replaceAll("\\s","").replaceAll("\\W","").trim().toLowerCase()).build())).limit(3);

    }

    public static String getUsername() {
        final Faker faker = new Faker();


        final String dragonBall = faker.elderScrolls().dragon();;
        final String name = faker.name().firstName();
        List<String> finalName = Arrays.asList(dragonBall, name);
        Collections.shuffle(finalName);

        return finalName.get(0);
    }
}



