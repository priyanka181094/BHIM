package com.bhim.npci.endtoend;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;

public class HomeTest extends BaseClass {

	@Test
	public void checkingBalanceAndVerifyTest() throws Throwable {
		Assert.assertTrue(
				home.getBankName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "bankName")),
				"The homepage is not displayed");
		home.getProfileMenu().click();
		Assert.assertTrue(
				profile.getMyProfile().getText().equals(
						eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "profilePageTitle")),
				"My profile page is not displayed");
		profile.clickOnAccounts(gestureUtility, eUtils);
		Assert.assertTrue(
				accounts.getBankAccount().getText().equals(
						eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "accountsPageTitle")),
				"Bank Accounts Page is not displayed");
		accounts.setBalanceIntoExcel(auth, eUtils);
		workLibrary.backNavigationTwice();
		Assert.assertTrue(
				home.getBankName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "bankName")),
				"The homepage is not displayed");
		home.getSelectBankAccount().click();
		Assert.assertTrue(
				accounts.getBankAccount().getText().equals(
						eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "accountsPageTitle")),
				"Bank Accounts Page is not displayed");
		accounts.checkBalance(accounts, auth);
		Assert.assertTrue(
				accounts.getActualBalance().getText().equals(eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "actualBalance")),
				"The actual balance and expected balance are not same");
		workLibrary.backNavigation();
	}
}
