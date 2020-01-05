package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected AndroidDriver driver;

    public BasePage(AndroidDriver driver) {this.driver = driver;}

    public void executeAdbShell(String adbShellCommand) {
        driver.executeScript("mobile: shell", ImmutableMap.of("command", adbShellCommand));
    }
/*
    public void setWait() {
        FluentWait<AndroidDriver> wait = new FluentWait<>((AndroidDriver) driver)
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class);
    }
*/

    public AndroidElement findLocator(By locator) {
        return (AndroidElement) driver.findElement(locator);
    }

    public void waitForLocator(By locator) {
        FluentWait wait = new FluentWait(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForLocator(locator);
        findLocator(locator).click();
    }

    public void sendKeys(By locator, String keys) {
        waitForLocator(locator);
        findLocator(locator).sendKeys(keys);
    }

    //Connection section

    public boolean isDataOn() {
        boolean connectionStatus = (driver.getConnection().isDataEnabled());
        return connectionStatus;
    }

    public boolean isWifiOn() {
        boolean connectionStatus = (driver.getConnection().isWiFiEnabled());
        return connectionStatus;
    }

    public boolean isSsidConnected() {
        try {
            executeAdbShell("dumpsys netstats | grep -E 'iface=wlan.*networkId'");
            return true;
        }
        catch(Exception e) {return false;}
    }

    public boolean isConnectionOn() {
        boolean isConnectionOn = isDataOn() || (isWifiOn() && isSsidConnected());
        System.out.println(isDataOn());
        System.out.println(isWifiOn());
        System.out.println(isSsidConnected());
        System.out.println("is connection ON");
        System.out.println(isConnectionOn);
        return isConnectionOn;
    }

    private void toggleWifi() {
        driver.toggleWifi();
    }

    public void turnConnectionOff(boolean isConnectionOn) {
        if (isConnectionOn) {
            toggleWifi();
        }
    }

    public void turnConnectionOn(boolean isConnectionOn) {
        if (!isConnectionOn) {
            toggleWifi();}
    }

}
