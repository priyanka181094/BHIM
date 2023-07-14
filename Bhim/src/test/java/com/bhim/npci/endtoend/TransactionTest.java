package com.bhim.npci.endtoend;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;

import io.appium.java_client.AppiumBy;

public class TransactionTest extends BaseClass {
	@Test
	public void sendInvalidAmountTest() throws Throwable {
		Assert.assertTrue(
				home.getBankName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "bankName")),
				"The homepage is not displayed");
		home.getSendMoneyOption().click();
		contact.searchingAndSelectingContact(eUtils);
		Assert.assertTrue(
				sendMoney.getPayeeName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "payeeName")),
				"The payee name is not correct");
		Assert.assertTrue(
				sendMoney.getPayeeUPIId().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "payeeUPIId")),
				"The payee UPI Id is not correct");
		contact.sendingInvalidAmount(sendMoney, eUtils, auth);
		webUtils.explicitWaitForVisibility(wait, sendMoney.getToastElement());
		Assert.assertTrue(
				sendMoney.getToastElement().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "sendInvalidAmountTest", "failureText")),
				"The toast message received is wrong");
		workLibrary.backNavigationTwice();
	}

	@Test
	public void enteringWrongPinWhileSendingMoneyTest() throws Throwable {
		Assert.assertTrue(
				home.getBankName().getText().equals(
						eUtils.getDataFromExcel("Test Data", "enteringWrongPinWhileSendingMoneyTest", "bankName")),
				"The homepage is not displayed");
		home.getSendMoneyOption().click();
		contact.searchingAndSelectingContact(eUtils);
		Assert.assertTrue(
				sendMoney.getPayeeName().getText().equals(
						eUtils.getDataFromExcel("Test Data", "enteringWrongPinWhileSendingMoneyTest", "payeeName")),
				"The payee name is not correct");
		Assert.assertTrue(
				sendMoney.getPayeeUPIId().getText().equals(
						eUtils.getDataFromExcel("Test Data", "enteringWrongPinWhileSendingMoneyTest", "payeeUPIId")),
				"The payee UPI Id is not correct");
		contact.sendingMoney(sendMoney, eUtils);
		auth.enteringWrongPIN();
		Assert.assertTrue(
				contact.getCouldNotSendMoneyText().getText().equals(
						eUtils.getDataFromExcel("Test Data", "enteringWrongPinWhileSendingMoneyTest", "failureText")),
				"The toast message received is wrong");
		contact.getHomeButton().click();
	}

	@Test(enabled = false)
	public void viewSatement() throws Throwable {
		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
				"The homepage is not displayed");
		home.getTransactionMenu().click();
		Assert.assertTrue(
				transactions.getTransactionText().getText().equals(eUtils.readDataFromExcel("Statement", 0, 1)),
				"The transactions page is not displayed");
		transactions.getThreeDotOption().click();
		transactions.getDownload().click();
		transactions.getFromDate().click();
		// List<WebElement> allDates = transactions.getAllDates();
		WebElement dateToClick = driver.findElement(AppiumBy
				.xpath("//android.view.View[@content-desc='" + eUtils.readDataFromExcel("Statement", 1, 1) + "']"));
		for (;;) {
			try {
				dateToClick.click();
				break;
			} catch (Exception e) {
				transactions.getPreviousButton().click();
			}
		}
	}

	@Test
	public void sendingMoneyToContactAndValidateBalanceTest() throws Throwable, IOException {
		Assert.assertTrue(home.getBankName().getText().equals(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "bankName")),
				"The homepage is not displayed");
		home.getProfileMenu().click();
		Assert.assertTrue(
				profile.getMyProfile().getText().equals(eUtils.getDataFromExcel("Test Data",
						"sendingMoneyToContactAndValidateBalanceTest", "profilePageTitle")),
				"My profile page is not displayed");
		gestureUtility.scrollTillElement(driver,
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "elementText"));
		profile.getAccountsMenu().click();
		Assert.assertTrue(
				accounts.getBankAccount().getText().equals(eUtils.getDataFromExcel("Test Data",
						"sendingMoneyToContactAndValidateBalanceTest", "accountsPageTitle")),
				"Bank Accounts Page is not displayed");
		String actualBalanceBeforeTransaction = accounts.setBalanceIntoExcel(auth, eUtils);
		workLibrary.backNavigationTwice();
		home.getSendMoneyOption().click();
		contact.searchingAndSelectingContact(eUtils);
		Assert.assertTrue(sendMoney.getPayeeName().getText().equals(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "payeeName")),
				"The payee name is not correct");
		Assert.assertTrue(
				sendMoney.getPayeeUPIId().getText().equals(eUtils.getDataFromExcel("Test Data",
						"sendingMoneyToContactAndValidateBalanceTest", "payeeUPIId")),
				"The payee UPI Id is not correct");
		contact.sendingMoney(sendMoney, eUtils);
		auth.enteringPIN();
		Assert.assertTrue(
				contact.getMoneySentText().getText().equals(eUtils.getDataFromExcel("Test Data",
						"sendingMoneyToContactAndValidateBalanceTest", "successText")),
				"The money sent page is not displayed");
		contact.getHomeButton().click();
		Assert.assertTrue(home.getBankName().getText().equals(
				eUtils.getDataFromExcel("Test Data", "sendingMoneyToContactAndValidateBalanceTest", "bankName")),
				"The homepage is not displayed");
		home.getSelectBankAccount().click();
		Assert.assertTrue(
				accounts.getBankAccount().getText().equals(eUtils.getDataFromExcel("Test Data",
						"sendingMoneyToContactAndValidateBalanceTest", "accountsPageTitle")),
				"Bank Accounts Page is not displayed");
		accounts.verifyBalanceAfterDeduction(eUtils, auth, actualBalanceBeforeTransaction);

	}

	@Test
	private void payThroughScanner() throws Throwable, IOException {
		Assert.assertTrue(
				home.getBankName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "bankName")),
				"The homepage is not displayed");
		home.getQrScanner().click();
		Assert.assertTrue(
				scan.getScanAndPayText().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "scanAndPayPageTitle")),
				"The scan and pay page is not displayed");
		
		scan.selectQRFromGallery(eUtils);
		Assert.assertTrue(
				scan.getScanAndPayText().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "scanAndPayPageTitle")),
				"The scan and pay page is not displayed");
		Assert.assertTrue(sendMoney.getPayeeName().getText().equals(
				eUtils.getDataFromExcel("Test Data", "payThroughScanner", "payeeName")),
				"The payee name is not correct");
		Assert.assertTrue(
				sendMoney.getPayeeUPIId().getText().equals(eUtils.getDataFromExcel("Test Data",
						"payThroughScanner", "payeeUPIId")),
				"The payee UPI Id is not correct");
		scan.sendingMoney(sendMoney, eUtils, contact);
		auth.enteringPIN();
		Assert.assertTrue(
				contact.getMoneySentText().getText().equals(eUtils.getDataFromExcel("Test Data",
						"payThroughScanner", "successText")),
				"The money sent page is not displayed");
		contact.getHomeButton().click();
	}
}
