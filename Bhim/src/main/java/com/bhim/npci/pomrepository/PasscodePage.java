package com.bhim.npci.pomrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for passcode page
 */
public class PasscodePage {
	AndroidDriver driver;

	public PasscodePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='passcode']")
	private WebElement passcodePageHeader;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='5']")
	private WebElement number5;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3']")
	private WebElement number3;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='8']")
	private WebElement number8;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1']")
	private WebElement number1;

	public WebElement getNumber5() {
		return number5;
	}

	public WebElement getNumber3() {
		return number3;
	}

	public WebElement getNumber8() {
		return number8;
	}

	public WebElement getNumber1() {
		return number1;
	}
	
	public WebElement getPasscodePageHeader() {
		return passcodePageHeader;
	}

	/**
	 * @author piyus
	 * This method is used to login to the app
	 */
	public void loginToApp() {
		number5.click();
		number3.click();
		number8.click();
		number1.click();
	}
}
