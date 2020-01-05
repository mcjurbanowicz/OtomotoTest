package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class PartsSetup{

    public static String apkPackage = "pl.otomoto";
    public static String apkActivity = "com.fixeads.verticals.cars.startup.view.activities.StartupActivity";

    protected AndroidDriver driver;

    protected AndroidDriver setUpAndroid() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Samsung J3 SM-J330F");
        caps.setCapability("udid", "4200ec3ba89494a1");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", apkPackage);
        caps.setCapability("appActivity", apkActivity);
        caps.setCapability("browserName", "");

        AndroidDriver driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        FluentWait<AndroidDriver> wait = new FluentWait<>((AndroidDriver) driver)
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class);

        return this.driver = driver;
    }
    public void executeAdbShell(String adbShellCommand) {
        driver.executeScript("mobile: shell", ImmutableMap.of("command", adbShellCommand));
    }
}
