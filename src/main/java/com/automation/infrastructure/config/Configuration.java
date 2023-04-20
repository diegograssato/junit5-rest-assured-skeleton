package com.automation.infrastructure.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

import java.net.URI;


@Config.HotReload
@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "classpath:config.${ENVIRONMENT}.properties",
        "classpath:config.properties",
        "system:properties",
        "system:env"})
public interface Configuration extends Config {

    @Key("api.base.path")
    String basePath();
    @Key("api.jsontest.uri")
    URI jsontestPath();

    @Key("api.cep.uri")
    URI cepPath();

    @Key("api.base.uri")
    String baseURI();

    @Key("log.all")
    boolean logAll();

    @Key("contract.path")
    String contractPath();

    @Key("contract.extension")
    String contractExtension();
}