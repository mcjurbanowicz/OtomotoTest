package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import tests.PartsSetup;

import java.util.concurrent.TimeUnit;

public class PartsPage extends BasePage {
    By tryAgainConnection_Button = By.id("pl.otomoto:id/activity_startup_splash_error_button");
    By czesci_Button = By.xpath("//*[@class='androidx.appcompat.app.ActionBar$b' and ./*[./*[@text='CZĘŚCI']]]");
    By moreFilters_Text = By.id("pl.otomoto:id/show_more_filters");
    //String rodzajCzesci_String = "Rodzaj części";
    By rodzajCzesci_Field = By.xpath("//*[@text='Rodzaj części']");
    By field_List = By.className("android.widget.ListView");
    String rodzajCzesciSearch_String= "Felgi";
    By felgi_ListElement = By.xpath("//*[@text='Felgi']");
    By mainView_Element = By.id("pl.otomoto:id/drawer_layout");
    By markaPojazduSearch_Field = By.id("pl.otomoto:id/filter");
    //String markaPojazdu_String = "Marka pojazdu";
    By markaPojazdu_Field = By.xpath("//*[@text='Marka pojazdu']");
    String markaPojazduSendKeys_String = "M";
    String markaPojazduSearch_String = "Mercedes";
    By mercedesBenz_ListElement = By.xpath("//*[@text='Mercedes-Benz']");
    By submit_Button = By.id("pl.otomoto:id/btnSubmit");

    public PartsPage(AndroidDriver driver) {super(driver);}

    public PartsPage prepareFirstTest() {
        //System.out.println(isConnectionOn());

        //turnConnectionOff(isConnectionOn());
        //System.out.println(isConnectionOn());
        executeAdbShell("wm density 280");
        executeAdbShell("settings put system font_scale 1.0");
        executeAdbShell(("am start -n " + PartsSetup.apkPackage + "/" + PartsSetup.apkActivity));
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //create  for wait method

        return new PartsPage(driver);
    }

    public PartsPage partsInternetOff() {

        click(tryAgainConnection_Button);
        turnConnectionOn(isConnectionOn());

        click(tryAgainConnection_Button);
        return new PartsPage(driver);
    }

    public PartsPage partsInternetOn() {
        //turnConnectionOn(isConnectionOn());
        //driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

        click(czesci_Button);
        click(moreFilters_Text);

        //Fix
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(1)).scrollIntoView(new UiSelector().childSelector(new UiScrollable().textContains(\""+rodzajCzesci_String+"\")).instance(1))");
        click(rodzajCzesci_Field);
        waitForLocator(field_List);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+rodzajCzesciSearch_String+"\").instance(0))");
        click(felgi_ListElement);
        waitForLocator(mainView_Element);
        click(markaPojazdu_Field);
        sendKeys(markaPojazduSearch_Field, markaPojazduSendKeys_String);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+markaPojazduSearch_String+"\").instance(0))");
        click(mercedesBenz_ListElement);
        waitForLocator(mainView_Element);
        click(submit_Button);
        return new PartsPage(driver);
    }
}
