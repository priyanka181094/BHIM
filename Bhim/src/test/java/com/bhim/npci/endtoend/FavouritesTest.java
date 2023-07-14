package com.bhim.npci.endtoend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bhim.npci.genericutility.BaseClass;
import com.bhim.npci.genericutility.ListenerImpClass;

public class FavouritesTest extends BaseClass {
	@Test
	public void addingFavouritesTest() throws Throwable {
		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
				"The homepage is not displayed");
		home.getManageOption().click();
		Assert.assertTrue(favourites.getFavourites().getText().equals("Favourites"),
				"Favourites page is not displayed");
		favourites.getAddNewFavouritesButton().click();
		favourites.getEnterUPIIdTextField().sendKeys(eUtils.readDataFromExcel("Favourites", 0, 1));
		List<WebElement> allElements = favourites.getAllContacts();
		for (WebElement element : allElements) {
			if (element.getText().contains(eUtils.readDataFromExcel("Favourites", 1, 1))) {
				element.click();
				break;
			}
		}
		Assert.assertTrue(favourites.getAddToFavouritesDialogueBox().getText().equals("Add to favourites"),
				"The add to favourites dialogue box is not displayed");
		Assert.assertTrue(favourites.getDisplayedName().getText().equals(eUtils.readDataFromExcel("Favourites", 2, 1)),
				"The name displayed is wrong");
		Assert.assertTrue(
				favourites.getDisplayedNumber().getText().equals(eUtils.readDataFromExcel("Favourites", 1, 1)),
				"The number displayed is wrong");
		favourites.getAddToFavouriteButton().click();
		favourites.getBackButton().click();
	}

	@Test
	public void addingFavouritesInProfile() throws Throwable {
		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
				"The homepage is not displayed");
		home.getProfileMenu().click();
		Assert.assertTrue(profile.getMyProfile().getText().equals("My Profile"), "My profile page is displayed");
		gestureUtility.scrollTillElement(driver, "Favourites");
		profile.getFavouritesMenu().click();
		Assert.assertTrue(favourites.getFavourites().getText().equals("Favourites"),
				"Favourites page is not displayed");
		favourites.getAddNewFavouritesButton().click();
		favourites.getEnterUPIIdTextField().sendKeys(eUtils.readDataFromExcel("Favourites", 3, 1));
		List<WebElement> allElements = favourites.getAllContacts();
		for (WebElement element : allElements) {
			if (element.getText().contains(eUtils.readDataFromExcel("Favourites", 4, 1))) {
				element.click();
				break;
			}
		}
		Assert.assertTrue(favourites.getAddToFavouritesDialogueBox().getText().equals("Add to favourites"),
				"The add to favourites dialogue box is not displayed");
		Assert.assertTrue(favourites.getDisplayedName().getText().equals(eUtils.readDataFromExcel("Favourites", 5, 1)),
				"The name displayed is wrong");
		Assert.assertTrue(
				favourites.getDisplayedNumber().getText().equals(eUtils.readDataFromExcel("Favourites", 4, 1)),
				"The number displayed is wrong");
		favourites.getAddToFavouriteButton().click();
		favourites.getBackButton().click();
		favourites.getBackButton().click();
	}

	@Test
	public void removeFavourite() throws Throwable {
		Assert.assertTrue(home.getBankName().getText().equals(eUtils.readDataFromExcel("Home", 0, 1)),
				"The homepage is not displayed");
		home.getManageOption().click();
		Assert.assertTrue(favourites.getFavourites().getText().equals("Favourites"),
				"Favourites page is not displayed");
		List<WebElement> contacts = favourites.getAllContacts();
		for (WebElement oneElement : contacts) {
			if (oneElement.getText().equals(eUtils.readDataFromExcel("Favourites", 1, 1))) {
				oneElement.click();
				break;
			}
		}
		Assert.assertTrue(favourites.getProceedPopup().getText().equals(eUtils.readDataFromExcel("Favourites", 7, 1)),
				"The proceed popup text is incorrect");
		favourites.getDeleteButton().click();
		Assert.assertTrue(favourites.getToastElement().getText().equals(eUtils.readDataFromExcel("Favourites", 6, 1)),
				"The toast message displayed is wrong");
		
		for (WebElement oneElement : contacts) {
			if (oneElement.getText().equals(eUtils.readDataFromExcel("Favourites", 4, 1))) {
				oneElement.click();
				break;
			}
		}
		Assert.assertTrue(favourites.getProceedPopup().getText().equals(eUtils.readDataFromExcel("Favourites", 7, 1)),
				"The proceed popup text is incorrect");
		favourites.getDeleteButton().click();
		Assert.assertTrue(favourites.getToastElement().getText().equals(eUtils.readDataFromExcel("Favourites", 6, 1)),
				"The toast message displayed is wrong");
		driver.navigate().back();
	}
}
