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

public class RealDeviceDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(ConfigDriver.getRealDeviceConfig().automationName())
                .setPlatformName(ConfigDriver.getRealDeviceConfig().platformName())
                .setPlatformVersion(ConfigDriver.getRealDeviceConfig().platformVersion())
                .setDeviceName(ConfigDriver.getRealDeviceConfig().deviceName())
                .setApp(getAppPath())
                .setAppPackage(ConfigDriver.getRealDeviceConfig().appPackage())
                .setAppActivity(ConfigDriver.getRealDeviceConfig().appActivity());
        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(ConfigDriver.getRealDeviceConfig().url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        String appVersion = ConfigDriver.getRealDeviceConfig().appVersion();
        String fileName = ConfigDriver.getRealDeviceConfig().fileName();
        String appUrl = ConfigDriver.getRealDeviceConfig().appUrl() + appVersion + fileName;
        String appPath = ConfigDriver.getRealDeviceConfig().appPath() + fileName;

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
