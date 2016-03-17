package uk.be2co.testcases;

import forall.annotations.DataSource;
import forall.core.BaseTest;
import forall.enums.Data;
import forall.utils.DataProviderUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import uk.be2co.pageobject.HomePage;
import uk.be2co.pageobject.LoginPage;
import uk.be2co.preset.BROWSER;
import uk.be2co.preset.URL;

import static forall.utils.DataProviderUtils.GENERIC_DP;
import static uk.be2co.preset.PageObjectSupplier.$;
import static uk.be2co.preset.PageObjectSupplier.loadSiteUrl;
import static uk.be2co.preset.PageObjectSupplier.loadSiteBrowser;

public class LoginPageTest extends BaseTest {

	/*@Test
	public void printParams() {
	}*/

	@DataSource(fileName = Data.MAIN, workSheetName = "Users")
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void loginWithAValidUser(final String email, final String password, final String message) {
		//System.out.println(email + ":" + password + " -> " + userName); // print var names
		//System.out.println(randomAlphanumeric(100)); // random from apache utils
		loadSiteUrl(URL.QA)
		loadSiteBrowser(BROWSER.FIREFOX)
				.setUserName(email)
				.setPassword(password)
				.clickLoginButton();
		Assert.assertEquals($(HomePage.class).getUserName(), message);
		//cutName не понимает
	}

	@DataSource(fileName = Data.MAIN, workSheetName = "Users")
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void loginWithInvalidUser(final String email, final String password, final String message) {
		loadSiteUrl(URL.QA)
				.setUserName(email)
				.setPassword(password)
				.clickLoginButton();
		Assert.assertEquals($(LoginPage.class).getErrorMessage(), message);

	}


	@DataSource(fileName = Data.MAIN, workSheetName = "Users")
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void loginWithInvalidPassword(final String email, final String password, final String message) {
		loadSiteUrl(URL.QA)
				.setUserName(email)
				.setPassword(password)
				.clickLoginButton();
		Assert.assertEquals($(LoginPage.class).getErrorMessage(), message);

	}

	//note


  /*  @DataSource(fileName = Data.MAIN, workSheetName = "Users")
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void loginWithAValidUser(final String email, final String password, final String userName) {
        System.out.println(email + ":" + password + " -> " + userName);
        System.out.println(randomAlphanumeric(100));
        Assert.assertEquals($(HomePage.class).getUserName(), "You are logged in as Seven");
    }

    @DataSource(fileName = Data.MAIN, workSheetName = "Users")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void loginWithInvalidUser(final String email, final String password, final String userName) {
        System.out.println(email + ":" + password + " -> " + userName);
        System.out.println(randomAscii(200));
    }*/


//test
//------------------------Test with data provider
/*
    @DataProvider(name = "dp")
    public static Iterator<Object[]> provideExcelData() {
        List<Object[]> output = new ArrayList<>();
        output.add(new Object[] {"shseven@hotmail.com", "test1234"});
        return output.iterator();
    }

    @DataProvider(name = "dp1")
    public static Iterator<Object[]> provideExcelData1() {
        List<Object[]> output = new ArrayList<>();
        output.add(new Object[] {"shseven@hotmail.com", "test1234", "error message"});
        output.add(new Object[] {"shseven111111222@hotmail.com", "1234tttttt", "Email address and password do not match"});
        return output.iterator();
    }

    @Test(dataProvider = "dp", enabled = false)
    public void correctUserNameLoginPage(String userName, String password) {
        loadSiteUrl(URL.DEV)
                .setUserName(userName)
                .setPassword(password)
                .clickLoginButton();

        Assert.assertEquals($(HomePage.class).getUserName(), "You are logged in as Seven");
    }



    @Test(dataProvider = "dp1", enabled = false)
    public void incorrectUserNameLoginPage(String userName, String password, String errorMessage){
        loadSiteUrl(URL.DEV)
                .setUserName(userName)
                .setPassword(password);

        Assert.assertEquals($(LoginPage.class).getErrorMessage(), errorMessage);
    }

*/


//-------------------------

/*

        // private static final String URL = "http://www.be2.co.uk/";
        private static final String URLLogin = "http://app.be2.co.uk/login/auth";

        private String UserName = "shseven@hotmail.com";
        private String Password = "test1234";

        private String wrongUserName = "shseven111111222@hotmail.com";
        private String wrongPassword = "1234tttttt";



        @Test
        public void СorrectUserNameLoginPage() {
            loadSiteUlr(URLLogin);
            String userName = new LoginPage()
                    .setUserName(UserName)
                    .setPassword(Password)
                    .clickLoginButton()
                    .getUserName();

            Assert.assertEquals(userName, "You are logged in as Seven");
        }


        //    .clickLoginButton() возвращает новый page,  в случае негативного сценария page не возвращается. Как нам переделать с clickLoginButton метод  ?
        //    сейчас я создала новый .clickLoginButtonNegative() метод который возвращает новую ErrorLoginPage  страницу
        @Test
        public void IncorrectUserNameLoginPage(){
            loadSiteUlr(URLLogin);
            String errorMessageText = new LoginPage()
                    .setUserName(wrongUserName)
                    .setPassword(Password)
                    .clickLoginButtonNegative()
                    .getErrorMessage();

            Assert.assertEquals(errorMessageText, "Email address and password do not match");
        }

        @Test
        public void IncorrectPasswordLoginPage(){
            loadSiteUlr(URLLogin);
            String errorMessageText = new LoginPage()
                    .setUserName(UserName)
                    .setPassword(wrongPassword)
                    .clickLoginButtonNegative()
                    .getErrorMessage();

            Assert.assertEquals(errorMessageText, "Email address and password do not match");
        }
*/

}