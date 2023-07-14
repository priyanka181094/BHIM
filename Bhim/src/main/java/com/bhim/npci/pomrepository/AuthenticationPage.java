package com.bhim.npci.pomrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AuthenticationPage {
	AndroidDriver driver;

	public AuthenticationPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='in.org.npci.upiapp:id/form_item_input']")
	private WebElement enterPinTextField;
	
	@AndroidFindBy(xpath = "//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[4]/android.widget.ImageView[2]")
	private WebElement approveButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/form_item_title']")
	private WebElement enterPINText;

	public WebElement getEnterPinTextField() {
		return enterPinTextField;
	}

	public WebElement getApproveButton() {
		return approveButton;
	}

	public WebElement getEnterPINText() {
		return enterPINText;
	}
	/**
	 * @author piyus
	 * This method is used to enter the upi pin and click on submit button
	 */
	public void enteringPIN() {
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[3]/android.widget.TextView[2]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[3]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[2]/android.widget.TextView[2]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[1]")).click();
//		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[2]")).click();
//		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[2]")).click();
		approveButton.click();
	}
	
	/**
	 * @author piyus
	 * This method is used to enter the wrong upi pin and click on submit button
	 */
	public void enteringWrongPIN() {
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[1]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[2]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[1]/android.widget.TextView[3]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[2]/android.widget.TextView[1]")).click();
//		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[2]/android.widget.TextView[2]")).click();
//		driver.findElement(AppiumBy.xpath("//android.widget.TableLayout[@resource-id='in.org.npci.upiapp:id/fragmentTelKeyboard']//android.widget.TableRow[2]/android.widget.TextView[3]")).click();
		approveButton.click();
	}
}
