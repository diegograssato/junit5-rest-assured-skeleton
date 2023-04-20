package com.automation.extension;


import com.automation.infrastructure.config.Configuration;
import com.automation.infrastructure.config.ConfigurationManager;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;


public class EnvironmentExtension implements BeforeAllCallback {
    final static Configuration configuration = ConfigurationManager.getConfiguration();

    public EnvironmentExtension() {

    }

    public EnvironmentExtension(Builder builder) {

    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Optional<String> opt = Optional.ofNullable(System.getenv("_ENVIRONMENT"));
        final String env = opt.map(String::trim).orElse("default");

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("BFF", String.valueOf(configuration.jsontestPath()))
                        .put("URL", String.valueOf(configuration.baseURI()))
                        .put("CEP", String.valueOf(configuration.cepPath()))
                        .put("ENVIRONMENT", env)
                        .build());
    }


    public static class Builder {


        public EnvironmentExtension build() {

            return new EnvironmentExtension(this);
        }

    }

}
