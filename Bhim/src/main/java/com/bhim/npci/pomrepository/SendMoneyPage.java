package com.bhim.npci.pomrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**@author piyus
 * This class contains the locators and business libraries for send money page
 */
public class SendMoneyPage {
	AndroidDriver driver;

	public SendMoneyPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeName']")
	private WebElement payeeName;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='payeeUpiID']")
	private WebElement payeeUPIId;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@hint='00.00']")
	private WebElement amountTextField;
	
	@AndroidFindBy(accessibility = "Send")
	private WebElement sendButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/snackbar_text']")
	private WebElement toastElement;

	public WebElement getPayeeName() {
		return payeeName;
	}

	public WebElement getPayeeUPIId() {
		return payeeUPIId;
	}

	public WebElement getAmountTextField() {
		return amountTextField;
	}

	public WebElement getSendButton() {
		return sendButton;
	}

	public WebElement getToastElement() {
		return toastElement;
	}
	
}
