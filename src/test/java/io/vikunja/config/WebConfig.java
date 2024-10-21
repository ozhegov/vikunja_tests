package io.vikunja.config;

import org.aeonbits.owner.Config;

public interface WebConfig extends Config {
    @DefaultValue("https://try.vikunja.io")
    String baseUrl();

    @DefaultValue("https://try.vikunja.io")
    String baseURI();

    @DefaultValue("/api/v1")
    String basePath();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("120.0")
    String browserVersion();

    String remoteHost();
}
