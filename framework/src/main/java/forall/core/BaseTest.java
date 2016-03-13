package forall.core;

import forall.utils.XMLConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import static forall.utils.ExcelUtils.clearDataStorage;

/**
 * Created by olja on 06/01/16.
 */
public class BaseTest {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return DRIVER.get();
	}

	@BeforeMethod
	public void setUp(ITestContext context) {
		XMLConfig xmlUtils = new XMLConfig(context.getCurrentXmlTest().getAllParameters());

		WebDriver driver;
		switch (xmlUtils.getParameter("browser")) { // read browser value from testng xml
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "/Users/oljashabanova/_dev/_AutomationPractice/browser_driver/chromedriver");
				driver = new ChromeDriver();
				break;
			default:
			case "firefox":
				driver = new FirefoxDriver();
				break;
		}

		DRIVER.set(driver);
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

	@AfterSuite
	public void cleanUp() {
		clearDataStorage();
	}
}
