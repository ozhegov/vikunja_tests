package io.vikunja.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "classpath:config/browserstack/${deviceName}.properties",
        "classpath:config/browserstack/samsung.properties"
})
public interface BrowserstackConfig extends Config {
    String app();

    String device();

    String version();

    String project();

    String build();

    String remoteUrl();
}
