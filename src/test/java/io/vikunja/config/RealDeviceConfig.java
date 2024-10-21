package io.vikunja.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/real.properties"
})
public interface RealDeviceConfig extends Config {
    String automationName();

    String platformName();

    String platformVersion();

    String deviceName();

    String appPackage();

    String appActivity();

    String url();

    String appVersion();

    String appUrl();

    String fileName();

    String appPath();
}
