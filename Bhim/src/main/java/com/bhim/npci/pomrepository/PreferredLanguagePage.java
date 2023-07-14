package com.bhim.npci.pomrepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.bhim.npci.genericutility.ExcelUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
/**
 * This class contains the locators and business libraries for preferred language page
 */
public class PreferredLanguagePage {
	AndroidDriver driver;

	public PreferredLanguagePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Change Language']")
	private WebElement languagePageText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=' Hello']")
	private WebElement englishOption;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Double tap to select')]/following-sibling::android.widget.TextView[2]")
	private List<WebElement> languageOptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=' (HINDI)']")
	private WebElement hindiOption;

	public WebElement getLanguagePageText() {
		return languagePageText;
	}

	public WebElement getEnglishOption() {
		return englishOption;
	}

	public List<WebElement> getLanguageOptions() {
		return languageOptions;
	}

	public WebElement getHindiOption() {
		return hindiOption;
	}
	
	/**
	 * @author piyus
	 * This method is used to select a particular language
	 * @param eUtils
	 * @throws Throwable
	 */
	public void selectingLanguage(ExcelUtility eUtils) throws Throwable {
		for (WebElement languageOption : languageOptions) {
			if (languageOption.getText().equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "language"))) {
				languageOption.click();
				break;
			}
		}
	}
	
	/**
	 * @author piyus
	 * This method is used to verify the language is changed or not
	 * @param eUtils
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	public WebElement changedNextButton(ExcelUtility eUtils) throws Throwable, IOException {
		WebElement nextButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='"
				+ eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "textToVerify")
				+ "']/android.widget.TextView"));
		return nextButton;
	}
}
