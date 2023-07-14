package com.bhim.npci.pomrepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.bhim.npci.genericutility.ExcelUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus This class contains the locators and business libraries for
 *         scan and pay page
 */
public class ScanAndPayPage {
	AndroidDriver driver;

	public ScanAndPayPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='load image from gallery']")
	private WebElement loadFromGallery;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']/following-sibling::android.view.View")
	private WebElement scanAndPayText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.oneplus.gallery:id/albumset_title']")
	private List<WebElement> allFolderNames;

	public WebElement getLoadFromGallery() {
		return loadFromGallery;
	}

	@AndroidFindBy(xpath = "//com.oplus.gallery.business_lib.ui.view.SlotView[@resource-id='com.oneplus.gallery:id/base_album_item_img']")
	private WebElement imageToSelect;

	public WebElement getScanAndPayText() {
		return scanAndPayText;
	}

	public List<WebElement> getAllFolderNames() {
		return allFolderNames;
	}

	public WebElement getImageToSelect() {
		return imageToSelect;
	}

	/**
	 * @author piyus This method is used for send money by scanning the QR code
	 * @param sendMoney
	 * @param eUtils
	 * @param contact
	 * @throws Throwable
	 * @throws Throwable
	 */
	public void sendingMoney(SendMoneyPage sendMoney, ExcelUtility eUtils, SendMoneyToContactPage contact)
			throws Throwable, Throwable {
		sendMoney.getAmountTextField().sendKeys(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "amount"));
		contact.getRemarksTextField().sendKeys(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "remark"));
		sendMoney.getSendButton().click();
		contact.getCheckBox().click();
		contact.getLaterButton().click();
	}

	public void selectQRFromGallery(ExcelUtility eUtils) throws Throwable, IOException {
		loadFromGallery.click();
		for (WebElement folderName : allFolderNames) {
			if (folderName.getText().equals(eUtils.getDataFromExcel("Test Data", "payThroughScanner", "folderName"))) {
				folderName.click();
				break;
			}
		}
		imageToSelect.click();
	}
}
