mvn -Dtest=CEPServicesTest test
mvn -Dtest=CEPServicesTest#iPerformGETOperation test

Tags
mvn -Dgroups=regression test

# Junit5 RestAssured-skeleton Skeleton

This is the simplest possible build script setup using Junit5 and RestAssured for testing and Allure report.
There is nothing fancy like a webapp or browser testing!


# API Automation Test BDD framework

API Automation Test is RestAssured based Junit5 framework to perform API testing. This project is useful as an example of API Testing with RestAssured and Java playing nicely together.

## Getting Started

Git:

    git clone https://github.com/diegograssato/junit5-rest-assured-skeleton.git
    cd junit5-rest-assured-skeleton
 

Or [download a zip](https://github.com/diegograssato/junit5-rest-assured-skeleton/archive/main.zip) file.

## Use Maven

Open a command window and run:

    ./mvnw test

With environment:

    ./mvnw clean test -DENVIRONMENT=local

Filter with test name:

    ./mvnw -Dtest=CEPServicesTest test
    ./mvnw -Dtest=CEPServicesTest#shouldPerformTOValidateSchemaParameterizedCEPTest test


Filter with Tags:
    
    ./mvnw -Dgroups=regression -DENVIRONMENT=local test
 

## Validating Json

For creating JSON schema of an API response:

Go to https://jsonschema.net/home

Paste the API response in the left side window and click on Submit button.

Your JSON schema file will be displayed on the right side window.

Copy and paste the JSON schema in the src/test/resources/contracts/<api-json-schema-name>.json file and rerun your test.

Validate your test using method `validationSchema("<api-json-schema-name>.json")`


