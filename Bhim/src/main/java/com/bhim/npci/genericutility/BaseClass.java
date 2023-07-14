package com.bhim.npci.genericutility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.bhim.npci.pomrepository.AuthenticationPage;
import com.bhim.npci.pomrepository.BankAccountsPage;
import com.bhim.npci.pomrepository.FavouritesPage;
import com.bhim.npci.pomrepository.FeedbackPage;
import com.bhim.npci.pomrepository.HomePage;
import com.bhim.npci.pomrepository.MyProfilePage;
import com.bhim.npci.pomrepository.PasscodePage;
import com.bhim.npci.pomrepository.PreferredLanguagePage;
import com.bhim.npci.pomrepository.ScanAndPayPage;
import com.bhim.npci.pomrepository.SendMoneyPage;
import com.bhim.npci.pomrepository.SendMoneyToContactPage;
import com.bhim.npci.pomrepository.TransactionsPage;
import com.bhim.npci.worklibrary.WorkLibrary;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
@Listeners(com.bhim.npci.genericutility.ListenerImpClass.class)
public class BaseClass {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public GestureUtility gestureUtility;
	public AndroidDriverUtility androidDriverUtility;
	public FileUtility fileUtils;
	public JSONUtility jsonUtility;
	public static AndroidDriver sdriver;
	public JavaUtility jutils;
	public ExcelUtility eUtils;
	public WebDriverUtility webUtils;
	public WebDriverWait wait;
	public PasscodePage passcode;
	public MyProfilePage profile;
	public HomePage home;
	public BankAccountsPage accounts;
	public FavouritesPage favourites;
	public AuthenticationPage auth;
	public SendMoneyPage sendMoney;
	public SendMoneyToContactPage contact;
	public PreferredLanguagePage lang;
	public FeedbackPage feedback;
	public TransactionsPage transactions;
	public WorkLibrary workLibrary;
	public ScanAndPayPage scan;

	@BeforeSuite
	public void config_BS() throws IOException {
		File file = new File(PathConstants.mainJS);
		fileUtils = new FileUtility();
		jsonUtility = new JSONUtility();
		service = new AppiumServiceBuilder().withAppiumJS(file)
				.withIPAddress(fileUtils.readDataFromPropertyFile("IPAddress"))
				.usingPort(Integer.parseInt(fileUtils.readDataFromPropertyFile("Port")))
				.withTimeout(Duration.ofSeconds(300)).build();
		service.start();
	}

	@BeforeClass
	public void config_BC() throws Throwable, IOException, ParseException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, fileUtils.readDataFromPropertyFile("PLATFORM_NAME"));
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, fileUtils.readDataFromPropertyFile("DEVICE_NAME"));
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, fileUtils.readDataFromPropertyFile("AUTOMATION_NAME"));
		dc.setCapability(MobileCapabilityType.UDID, fileUtils.readDataFromPropertyFile("UDID"));
		dc.setCapability("appPackage", fileUtils.readDataFromPropertyFile("appPackage"));
		dc.setCapability("appActivity", fileUtils.readDataFromPropertyFile("appActivity"));
		dc.setCapability("ignoreHiddenApiPolicyError",
				fileUtils.readDataFromPropertyFile("ignoreHiddenApiPolicyError"));
		dc.setCapability("autoGrantPermissions", fileUtils.readDataFromPropertyFile("autoGrantPermissions"));
		dc.setCapability("noReset", fileUtils.readDataFromPropertyFile("noReset"));

		URL url = new URL(fileUtils.readDataFromPropertyFile("url"));
		driver = new AndroidDriver(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PathConstants.implicitWaitDuration));
		sdriver = driver;
		gestureUtility = new GestureUtility(driver);
		androidDriverUtility = new AndroidDriverUtility(driver);
		jutils = new JavaUtility(driver);
		eUtils = new ExcelUtility(driver);
		webUtils = new WebDriverUtility(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		passcode = new PasscodePage(driver);
		profile = new MyProfilePage(driver);
		home = new HomePage(driver);
		accounts = new BankAccountsPage(driver);
		favourites = new FavouritesPage(driver);
		auth = new AuthenticationPage(driver);
		sendMoney = new SendMoneyPage(driver);
		contact = new SendMoneyToContactPage(driver);
		lang = new PreferredLanguagePage(driver);
		feedback = new FeedbackPage(driver);
		transactions = new TransactionsPage(driver);
		workLibrary = new WorkLibrary(driver);
		scan = new ScanAndPayPage(driver);
	}

	@BeforeMethod
	public void config_BM() throws Throwable {
		Assert.assertTrue(
				passcode.getPasscodePageHeader().getText().equals(
						eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "passcodePageTitle")),
				"The passcode page is not displayed");
		passcode.loginToApp();
		webUtils.explicitWaitForClickable(wait, home.getSelectBankAccount());
	}

	@AfterMethod
	public void config_AM() throws Throwable {
		Assert.assertTrue(
				home.getBankName().getText()
						.equals(eUtils.getDataFromExcel("Test Data", "verifyLanguageTest", "bankName")),
				"The homepage is not displayed");
		home.getMoreOption().click();
		home.getLogout().click();
		Assert.assertTrue(
				passcode.getPasscodePageHeader().getText().equals(
						eUtils.getDataFromExcel("Test Data", "checkingBalanceAndVerifyTest", "passcodePageTitle")),
				"The passcode page is not displayed");
	}

	@AfterClass
	public void config_AC() {
		driver.quit();
	}

	@AfterSuite
	public void config_AS() {
		service.stop();
	}
}
