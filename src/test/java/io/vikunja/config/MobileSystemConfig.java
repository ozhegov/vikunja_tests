package io.vikunja.config;

import org.aeonbits.owner.Config;

public interface MobileSystemConfig extends Config {
    @DefaultValue("browserstack")
    String deviceHost();
}