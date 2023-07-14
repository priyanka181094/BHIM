package com.bhim.npci.endtoend;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bhim.npci.genericutility.BaseClass;

public class LanguageTest extends BaseClass {

	@Test
	public void verifyLanguageTest() throws Throwable {
		Assert.assertTrue(home.getBankName().getText().equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "bankName")),
				"The homepage is not displayed");
		home.getMoreOption().click();
		home.getChangeLanguageMenu().click();
		Assert.assertTrue(
				lang.getLanguagePageText().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "languagePageTitle")),
				"The change language page is not displayed");
		lang.selectingLanguage(eUtils);
		WebElement nextButton = lang.changedNextButton(eUtils);
		Assert.assertTrue(nextButton.getText().equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "textToVerify")),
				"The language is not changed");
		workLibrary.backNavigation();
	}
}
