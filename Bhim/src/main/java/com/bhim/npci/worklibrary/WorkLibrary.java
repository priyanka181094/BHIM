package com.bhim.npci.worklibrary;

import io.appium.java_client.android.AndroidDriver;

public class WorkLibrary {
	AndroidDriver driver;

	public WorkLibrary(AndroidDriver driver) {
		this.driver = driver;
	}

	public void backNavigationTwice() {
		driver.navigate().back();
		driver.navigate().back();
	}
	
	public void backNavigation() {
		driver.navigate().back();
	}
}
