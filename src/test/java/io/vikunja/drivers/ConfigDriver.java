package io.vikunja.drivers;

import io.vikunja.config.*;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class ConfigDriver {
    @Getter
    private static final AuthConfig authConfig =
            ConfigFactory.create(
                    AuthConfig.class,
                    System.getProperties()
            );
    @Getter
    private static final EmulationConfig emulationConfig =
            ConfigFactory.create(
                    EmulationConfig.class,
                    System.getProperties()
            );
    @Getter
    private static final RealDeviceConfig realDeviceConfig =
            ConfigFactory.create(
                    RealDeviceConfig.class,
                    System.getProperties()
            );
    @Getter
    private static final BrowserstackConfig browserstackConfig =
            ConfigFactory.create(
                    BrowserstackConfig.class,
                    System.getProperties()
            );
    @Getter
    private static final MobileSystemConfig mobileSystemConfig =
            ConfigFactory.create(
                    MobileSystemConfig.class,
                    System.getProperties()
            );
    @Getter
    private static final WebConfig webConfig =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );
}
