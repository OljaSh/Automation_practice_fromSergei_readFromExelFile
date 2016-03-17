package uk.be2co.preset;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BROWSER {
    FIREFOX, CHROME;

    public WebDriver getDriver(){
        WebDriver driver = null;

        switch (this){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/Users/oljashabanova/_dev/_AutomationPractice/browser_driver/chromedriver");
                driver = new ChromeDriver();
                break;
        }
       return driver;

    }
}
