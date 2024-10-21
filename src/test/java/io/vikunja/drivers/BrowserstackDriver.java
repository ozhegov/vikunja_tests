package io.vikunja.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", ConfigDriver.getAuthConfig().browserstackUsername());
        bstackOptions.put("accessKey", ConfigDriver.getAuthConfig().browserstackPassword());
        bstackOptions.put("projectName", ConfigDriver.getBrowserstackConfig().project());
        bstackOptions.put("buildName", ConfigDriver.getBrowserstackConfig().build());
        caps.setCapability("appium:platformVersion", ConfigDriver.getBrowserstackConfig().version());
        caps.setCapability("appium:deviceName", ConfigDriver.getBrowserstackConfig().device());
        caps.setCapability("appium:app", ConfigDriver.getBrowserstackConfig().app());
        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new AndroidDriver(
                    new URL(ConfigDriver.getBrowserstackConfig().remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}


