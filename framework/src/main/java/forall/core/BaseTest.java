package forall.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import static forall.utils.ExcelUtils.clearDataStorage;

/**
 * Created by olja on 06/01/16.
 */
public class BaseTest {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return DRIVER.get();
    }

    @BeforeMethod
    public void setUp(){
        DRIVER.set(new FirefoxDriver());
    }

    @AfterMethod
    public void tearDown(){
        if (getDriver() != null){
            getDriver().quit();
        }
    }

    @AfterSuite
    public void cleanUp() {
        clearDataStorage();
    }
}
