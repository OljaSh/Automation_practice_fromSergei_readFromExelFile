package uk.be2co.pageobject;

import org.openqa.selenium.By;
import forall.core.BasePage;

/**
 * Created by olja on 06/01/16.
 */
public class ErrorLoginPage extends BasePage {

    private By errorMessageText = By.id("errorMsg");

    public String getErrorMessage() {
        return getText(errorMessageText);
    }
}


