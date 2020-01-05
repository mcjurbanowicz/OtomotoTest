package tests;

import org.testng.annotations.*;
import pages.PartsPage;

public class PartsTest extends PartsSetup{

    @BeforeClass
    public void mainSetUp() throws Exception {
        setUpAndroid();
        executeAdbShell("pm clear pl.otomoto");
    }

    @AfterClass
    public void quitTest() throws Exception {
        driver.quit();
        driver = null;
    }

    @Test
    public void beforeFirstTest() throws  Exception {
        new PartsPage(driver).prepareFirstTest();
    }
/*
    @Test
    public void firstTest() throws InterruptedException {
        new PartsPage(driver).partsInternetOff();
    }
*/
    @Test
    public void secondTest() throws InterruptedException {
        new PartsPage(driver).partsInternetOn();
    }
}