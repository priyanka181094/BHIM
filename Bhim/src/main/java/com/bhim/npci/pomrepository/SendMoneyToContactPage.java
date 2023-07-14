package com.bhim.npci.pomrepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.ExcelUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**
 * @author piyus
 * This class contains the locators and business libraries for send money to contact page
 */
public class SendMoneyToContactPage {
	AndroidDriver driver;

	public SendMoneyToContactPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Send Money']")
	private WebElement sendMoneyPageTitle;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Contacts, tab']")
	private WebElement contactsTab;

	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement searchTextField;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'double tap to select contact')]/android.widget.TextView")
	private List<WebElement> allElements;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Remarks')]//android.widget.EditText")
	private WebElement remarksTextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='UPILiteText']")
	private WebElement upiLiteText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='checkBoxText']/preceding-sibling::android.view.ViewGroup[3]")
	private WebElement checkBox;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Later']/android.widget.TextView")
	private WebElement laterButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Could not send money!']")
	private WebElement couldNotSendMoneyText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Money sent!']")
	private WebElement moneySentText;

	@AndroidFindBy(accessibility = "Home")
	private WebElement homeButton;

	public WebElement getSendMoneyPageTitle() {
		return sendMoneyPageTitle;
	}

	public WebElement getContactsTab() {
		return contactsTab;
	}

	public WebElement getSearchTextField() {
		return searchTextField;
	}

	public List<WebElement> getAllElements() {
		return allElements;
	}

	public WebElement getRemarksTextField() {
		return remarksTextField;
	}

	public WebElement getCouldNotSendMoneyText() {
		return couldNotSendMoneyText;
	}

	public WebElement getMoneySentText() {
		return moneySentText;
	}

	public WebElement getHomeButton() {
		return homeButton;
	}

	public WebElement getUpiLiteText() {
		return upiLiteText;
	}

	public WebElement getCheckBox() {
		return checkBox;
	}

	public WebElement getLaterButton() {
		return laterButton;
	}

	/**
	 * @author piyus
	 * This method is used for searching and selecting a particular contact
	 * @param eUtils
	 * @throws Throwable
	 * @throws IOException
	 */
	public void searchingAndSelectingContact(ExcelUtility eUtils) throws Throwable, IOException {
		contactsTab.click();
		searchTextField.sendKeys(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "contactName"));
		for (WebElement oneContact : allElements) {
			if (oneContact.getText().equals(eUtils.getDataFromExcel("Test Data",
					"sendingMoneyToContactAndValidateBalanceTest", "contactNumber"))) {
				oneContact.click();
				break;
			}
		}
	}

	/**
	 * @author piyus
	 * This method is used for sending the money to any particular contact
	 * @param sendMoney
	 * @param eUtils
	 * @throws Throwable
	 * @throws Throwable
	 */
	public void sendingMoney(SendMoneyPage sendMoney, ExcelUtility eUtils) throws Throwable, Throwable {
		sendMoney.getAmountTextField().sendKeys(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "amount"));
		remarksTextField.sendKeys(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "remark"));
		sendMoney.getSendButton().click();
		checkBox.click();
		laterButton.click();
	}

	/**@author piyus
	 * This method is use for trying to send money by entering invalid amount
	 * @param sendMoney
	 * @param eUtils
	 * @param auth
	 * @throws Throwable
	 * @throws Throwable
	 */
	public void sendingInvalidAmount(SendMoneyPage sendMoney, ExcelUtility eUtils,
			AuthenticationPage auth) throws Throwable, Throwable {
		sendMoney.getAmountTextField()
				.sendKeys(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "amount"));
		remarksTextField.sendKeys(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "remark"));
		sendMoney.getSendButton().click();
	}
}
