package uk.be2co.preset;

import com.esotericsoftware.reflectasm.ConstructorAccess;

import uk.be2co.pageobject.LoginPage;

import static forall.core.BaseTest.getDriver;


public final class PageObjectSupplier {

	public static <T> T $(Class<T> pageObject) {
		return ConstructorAccess.get(pageObject).newInstance();
	}

	public static LoginPage loadSiteUrl(final URL url) {
		getDriver().get(url.toString());
		return $(LoginPage.class);
	}

	public static LoginPage loadSiteBrowser(final BROWSER browser){
		getDriver().get(browser.toString());
		return $(LoginPage.class);
	}
	private PageObjectSupplier() {
	}
}
