package uk.be2co.pageobject;

import org.openqa.selenium.By;
import forall.core.BasePage;

/**
 * Created by olja on 06/01/16.
 */
public class HomePage  extends BasePage {

    private By labelUserName = By.id("logged_user");

    public String getUserName(){
        return getText(labelUserName);
    }

}
