package com.bhim.npci.genericutility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

/**
 * @author 
 * piyus This class is used to perform all android driver related operations
 */
public class AndroidDriverUtility extends BaseClass {

	public AndroidDriverUtility(AndroidDriver driver) {
		this.driver = driver;
	}

	/**
	 * @author piyus This method is used to run the application in the background
	 * @param packageName
	 * @return
	 */
	public ApplicationState runAppInBackground(String packageName) {
		driver.runAppInBackground(Duration.ofSeconds(30));
		return driver.queryAppState(packageName);
	}

	/**
	 * @author piyus This method is used to install the application and check
	 *         whether it is installed or not
	 * @param apkPath
	 * @param packageName
	 * @return
	 */
	public boolean installApp(String apkPath, String packageName) {
		driver.installApp(apkPath);
		return driver.isAppInstalled(packageName);
	}

	/**
	 * @author piyus This method is used to uninstall the application
	 * @param packageName
	 * @return
	 */
	public boolean removeApp(String packageName) {
		driver.removeApp(packageName);
		return driver.isAppInstalled(packageName);
	}

	/**
	 * @author piyus This method is used to change orientation of device to
	 *         landscape
	 */
	public void orientationLandScape() {
		ScreenOrientation s = driver.getOrientation();
		driver.rotate(s.LANDSCAPE);
	}

	/**
	 * @author piyus This method is used to change orientation of device to portrait
	 */
	public void orientationPortrait() {
		ScreenOrientation s = driver.getOrientation();
		driver.rotate(s.PORTRAIT);
	}

	/**
	 * @author piyus This method is used to open the notification tray
	 */
	public void openNotification() {
		driver.openNotifications();
	}

	/**
	 * @author piyus This method is used to hide the keyboard
	 */
	public void hideKeyboard() {
		driver.hideKeyboard();
	}

	/**
	 * This method is used to check whether device is locked or not
	 * 
	 * @return
	 */
	public boolean deviceLockState() {
		return driver.isDeviceLocked();
	}

	/**
	 * @author piyus This method is used to get all context id's
	 */
	public void getContexts() {
		Set<String> contextID = driver.getContextHandles();
		for (String string : contextID) {
			System.out.println(string);
		}
	}

	/**
	 * @author piyus This method is used to switch context to web view
	 * @param contextId
	 */
	public void switchContext(String contextId) {
		driver.context(contextId);
	}
}
