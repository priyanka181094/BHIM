package com.bhim.npci.pomrepository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author piyus
 * This class contains the locators and business libraries for favourites page
 */
public class FavouritesPage {
	AndroidDriver driver;

	public FavouritesPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Favourites']")
	private WebElement favourites;
	
	@AndroidFindBy(accessibility = "Add New Favorite")
	private WebElement addNewFavouritesButton;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement enterUPIIdTextField;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='9668440298']")
	private WebElement contactToAddToFavourites;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='confirmToDeclineHeader']")
	private WebElement addToFavouritesDialogueBox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='name']")
	private WebElement displayedName;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='vpa']")
	private WebElement displayedNumber;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD']")
	private WebElement addToFavouriteButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SAGAR  SAHOO']")
	private WebElement addedFavourite;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.org.npci.upiapp:id/snackbar_text']")
	private WebElement toastElement;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Delete']")
	private WebElement deleteButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you want to proceed with?']")
	private WebElement proceedPopup;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'double tap to select contact')]/android.widget.TextView")
	private List<WebElement> allContacts;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']")
	private WebElement backButton;

	public WebElement getFavourites() {
		return favourites;
	}

	public WebElement getAddNewFavouritesButton() {
		return addNewFavouritesButton;
	}

	public WebElement getEnterUPIIdTextField() {
		return enterUPIIdTextField;
	}

	public WebElement getContactToAddToFavourites() {
		return contactToAddToFavourites;
	}

	public WebElement getAddToFavouritesDialogueBox() {
		return addToFavouritesDialogueBox;
	}
	
	public WebElement getDisplayedName() {
		return displayedName;
	}

	public WebElement getDisplayedNumber() {
		return displayedNumber;
	}

	public WebElement getAddToFavouriteButton() {
		return addToFavouriteButton;
	}

	public WebElement getAddedFavourite() {
		return addedFavourite;
	}

	public WebElement getToastElement() {
		return toastElement;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public WebElement getProceedPopup() {
		return proceedPopup;
	}

	public List<WebElement> getAllContacts() {
		return allContacts;
	}

	public WebElement getBackButton() {
		return backButton;
	}
	
}
