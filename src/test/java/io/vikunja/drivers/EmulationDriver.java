package io.vikunja.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class EmulationDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(ConfigDriver.getEmulationConfig().automationName())
                .setPlatformName(ConfigDriver.getEmulationConfig().platformName())
                .setPlatformVersion(ConfigDriver.getEmulationConfig().platformVersion())
                .setDeviceName(ConfigDriver.getEmulationConfig().deviceName())
                .setApp(getAppPath())
                .setAppPackage(ConfigDriver.getEmulationConfig().appPackage())
                .setAppActivity(ConfigDriver.getEmulationConfig().appActivity());
        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(ConfigDriver.getEmulationConfig().url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        String appVersion = ConfigDriver.getEmulationConfig().appVersion();
        String fileName = ConfigDriver.getEmulationConfig().fileName();
        String appUrl = ConfigDriver.getEmulationConfig().appUrl() + appVersion + fileName;
        String appPath = ConfigDriver.getEmulationConfig().appPath() + fileName;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application");
            }
        }
        return app.getAbsolutePath();
    }
}
