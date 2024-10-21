package io.vikunja.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "classpath:config/auth.properties"
})
public interface AuthConfig extends Config {
    String vikunjaUsername();

    String vikunjaPassword();

    String vikunjaServerAddress();

    String browserstackUsername();

    String browserstackPassword();

    String selenoidUsername();

    String selenoidPassword();
}
