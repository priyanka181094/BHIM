package com.bhim.npci.pomrepository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.bhim.npci.genericutility.ExcelUtility;
import com.bhim.npci.genericutility.GestureUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for my profile page
 */
public class MyProfilePage {
	AndroidDriver driver;

	public MyProfilePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to view all accounts')]")
	private WebElement accountsMenu;

	@AndroidFindBy(accessibility = "Favourites, double tap to view or modify favourites")
	private WebElement favouritesMenu;

	@AndroidFindBy(xpath = "//android.view.View[@text='My Profile']")
	private WebElement myProfile;

	public WebElement getAccountsMenu() {
		return accountsMenu;
	}

	public WebElement getFavouritesMenu() {
		return favouritesMenu;
	}

	public WebElement getMyProfile() {
		return myProfile;
	}

	/**
	 * @author piyus
	 * This method is used to scroll the page and click on accounts
	 * @param gestureUtility
	 * @param eUtils
	 * @throws Throwable
	 * @throws IOException
	 */
	public void clickOnAccounts(GestureUtility gestureUtility, ExcelUtility eUtils)
			throws Throwable, IOException {
		gestureUtility.scrollTillElement(driver,
				eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "elementText"));
		accountsMenu.click();
	}

}
