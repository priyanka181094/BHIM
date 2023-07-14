package com.bhim.npci.genericutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
/**
 * @author piyus
 * This class is used for web driver related operations
 */
public class WebDriverUtility {
	AndroidDriver driver;
	WebDriverWait wait;

	public WebDriverUtility(AndroidDriver driver) {
		this.driver = driver;
	}

	/**
	 * @author piyus
	 * This method is used to wait for element to be clickable
	 * @param wait
	 * @param element
	 */
	public void explicitWaitForClickable(WebDriverWait wait, WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * @author piyus
	 * This method is used to wait for element to be visible
	 * @param wait
	 * @param element
	 */
	public void explicitWaitForVisibility(WebDriverWait wait, WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
