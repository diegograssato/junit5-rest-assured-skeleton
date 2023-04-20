package com.automation.extension;


import com.automation.infrastructure.config.Configuration;
import com.automation.infrastructure.config.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class RestExtension implements BeforeEachCallback {

    private final String uri;
    private RequestSpecification requestSpecification;

    public RequestSpecification useSpec(){
        return this.requestSpecification;
    }


    public RequestSpecification useAuthBasic(final String username, final String password){
        return this.requestSpecification.auth().basic(username, password);
    }

    public RequestSpecification useAuthDigest(final String username, final String password){
        return this.requestSpecification.auth().digest(username, password);
    }

    public RequestSpecification useAuthForm(final String username, final String password){
        return this.requestSpecification.auth().form(username, password);
    }
    public RequestSpecification useAuthOauth1(final String consumerKey, final String consumerSecret, final String accessToken, final String tokenSecret){
        return this.requestSpecification.auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret);
    }

    public RequestSpecification useAuthOauth2(final String token){
        return this.requestSpecification.auth().oauth2(token);
    }

    public RestExtension(Builder builder) {

        this.uri = builder.uri;
    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {


        this.requestSpecification = RestAssured.given().spec(new RequestSpecBuilder().
                setBaseUri(this.uri).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                setRelaxedHTTPSValidation().
                addFilter(new AllureRestAssured()).
                build());

    }

    public static class Builder {

        final static Configuration configuration = ConfigurationManager.getConfiguration();
        private String uri;

        public Builder baseUri(final String uri) {


            this.uri = uri;

            return this;
        }

        public Builder useLoggingFilters() {

            if (configuration.logAll()) {
                RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
            } else {
                RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            }
            return this;
        }

        public RestExtension build() {
            this.baseUri((this.uri == null) ? configuration.baseURI() : this.uri);
            this.useLoggingFilters();
            return new RestExtension(this);
        }

        public RestExtension build(final String uri) {

            this.baseUri((uri == null) ? configuration.baseURI() : uri);
            this.useLoggingFilters();
            return new RestExtension(this);
        }
    }

}
