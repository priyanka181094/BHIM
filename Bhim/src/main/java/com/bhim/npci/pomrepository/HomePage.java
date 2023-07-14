package com.bhim.npci.pomrepository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for home page
 */
public class HomePage {
	AndroidDriver driver;

	public HomePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to change')]")
	private WebElement selectBankAccount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='bankName']")
	private WebElement bankName;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='More Options, Double tap to Explore']")
	private WebElement moreOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
	private WebElement logout;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='Manage']")
	private WebElement manageOption;

	@AndroidFindBy(accessibility = "Send Money, Double tap to send money")
	private WebElement sendMoneyOption;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Scan a QR code, Double tap to scan a QR code']")
	private WebElement qrScanner;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='SAGAR  SAHOO']")
	private WebElement favouriteContact;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']/parent::android.view.ViewGroup")
	private WebElement profileMenu;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Change Language']")
	private WebElement changeLanguageMenu;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='favouriteItem']/android.widget.TextView")
	private List<WebElement> allFavourites;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Send Feedback']")
	private WebElement sendFeedback;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Transactions']/parent::android.view.ViewGroup")
	private WebElement transactionMenu;

	public WebElement getSelectBankAccount() {
		return selectBankAccount;
	}

	public WebElement getBankName() {
		return bankName;
	}

	public WebElement getMoreOption() {
		return moreOption;
	}

	public WebElement getLogout() {
		return logout;
	}

	public WebElement getManageOption() {
		return manageOption;
	}

	public WebElement getSendMoneyOption() {
		return sendMoneyOption;
	}

	public WebElement getQrScanner() {
		return qrScanner;
	}

	public WebElement getFavouriteContact() {
		return favouriteContact;
	}

	public WebElement getProfileMenu() {
		return profileMenu;
	}

	public WebElement getChangeLanguageMenu() {
		return changeLanguageMenu;
	}

	public List<WebElement> getAllFavourites() {
		return allFavourites;
	}

	public WebElement getSendFeedback() {
		return sendFeedback;
	}

	public WebElement getTransactionMenu() {
		return transactionMenu;
	}
	
}