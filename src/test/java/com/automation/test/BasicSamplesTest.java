package com.automation.test;

import com.automation.extension.EnvironmentExtension;
import com.automation.extension.RestExtension;
import com.automation.definition.ValidationDefinitions;
import com.automation.resolver.MyParameterResolver;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@Epic("Epic basic samples")
@Story("History about the basic samples")
@DisplayName("History about the basic samples")
@ExtendWith(EnvironmentExtension.class)
public class BasicSamplesTest extends ValidationDefinitions {

//    @RegisterExtension
//    EnvironmentExtension environmentExtension = EnvironmentExtension.builder().build();

    @RegisterExtension
    RestExtension restExtension = RestExtension.builder().build();

    @Test
    @Description("Testing the GET has been received value")
    @DisplayName("Testing the GET has been received value")
    public void shouldPerformTORecievedValueUsingRestService() throws Exception {
        // Give
        final String url = "/get";
        final HashMap<String, String> body = new HashMap<>();
        body.put("name", "diego");

        // When
        log.info("Requesting GET: {}", url);
        response = restExtension.useSpec().queryParams(body).get(url);

        // Then
        this.verifyResponseStatusValue(200);
        this.verifyResponseKeyValue("args.name", "diego");

    }

    @Test
    @ExtendWith(MyParameterResolver.class)
    @Description("Testing the using extender with")
    @DisplayName("Testing the using extender with")
    public void shouldPerformTORecievedUsingExtendWithResolver(final Object parameter) throws Exception {

        log.info("ExtendWith parameter : {}", parameter);
        assertThat(parameter, is("dtux"));
    }

}