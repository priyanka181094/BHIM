package com.bhim.npci.pomrepository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.bhim.npci.genericutility.ExcelUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**
 * @author piyus
 * This class contains the locators and business libraries for bank accounts page
 */
public class BankAccountsPage {
	AndroidDriver driver;

	public BankAccountsPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(xpath = "//android.view.View[@text='Bank Accounts']")
	private WebElement bankAccount;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Check Balance']")
	private WebElement checkBalanceOption;
	
	@AndroidFindBy(accessibility = "Double tap to expand")
	private WebElement expandOption;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='NOT NOW']")
	private WebElement feedBackDecline;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='actualBalance']")
	private WebElement actualBalanceText;

	
	public WebElement getBankAccount() {
		return bankAccount;
	}

	public WebElement getActualBalance() {
		return actualBalanceText;
	}

	public WebElement getCheckBalanceOption() {
		return checkBalanceOption;
	}

	public WebElement getExpandOption() {
		return expandOption;
	}

	public WebElement getFeedBackDecline() {
		return feedBackDecline;
	}
	
	/**
	 * @author piyus
	 * This method is used to insert actual balance generated to excel sheet
	 * @param auth
	 * @param eUtils
	 * @throws Throwable
	 * @throws IOException
	 */
	public String setBalanceIntoExcel(AuthenticationPage auth, ExcelUtility eUtils) throws Throwable, IOException {
		checkBalanceOption.click();
		auth.enteringPIN();
		String actualBalance = actualBalanceText.getText();
		eUtils.setDataIntoExcel("Test Data", "checkingBalanceAndVerifyTest", "actualBalance", actualBalance);
		return actualBalance;
	}
	
	/**
	 * @author piyus
	 * This method is used to check the balance by enetering pin
	 * @param accounts
	 * @param auth
	 */
	public void checkBalance(BankAccountsPage accounts, AuthenticationPage auth) {
		accounts.getCheckBalanceOption().click();
		auth.enteringPIN();
	}
	
	/**
	 * @author piyus
	 * This method is used to verify the balance after the deduction
	 * @param eUtils
	 * @param auth
	 * @param actualBalanceBeforeTransaction
	 * @throws Throwable
	 * @throws IOException
	 */
	public void verifyBalanceAfterDeduction(ExcelUtility eUtils, AuthenticationPage auth,
			String actualBalanceBeforeTransaction) throws Throwable, IOException {
		checkBalanceOption.click();
		auth.enteringPIN();
		String actualBalanceAfterTransaction = actualBalanceText.getText();
		String actualBalanceAfterTransactionModified = actualBalanceAfterTransaction.replace("₹ ", "");
		String actualBalanceBeforeTransactionModified = actualBalanceBeforeTransaction.replace("₹ ", "");
		double expectedBalanceAfterTransactionModified = Double.parseDouble(actualBalanceBeforeTransactionModified)
				- Double.parseDouble(
						eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "amount")
								.replace("₹ ", ""));
		String expectedBalanceAfterTransaction = String.valueOf(expectedBalanceAfterTransactionModified);
		Assert.assertEquals(actualBalanceAfterTransactionModified, expectedBalanceAfterTransaction);
		driver.navigate().back();
	}
}
