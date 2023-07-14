package com.bhim.npci.genericutility;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImpClass extends BaseClass implements ITestListener,IReporter{
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		Reporter.log(testName + "--------TEST SCRIPT EXECUTION STARTED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.PASS, testName + "---------PASS");
		Reporter.log(testName + "--------EXECUTED SUCCESSFULLY");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.FAIL, testName + "---------FAIL");
		test.log(Status.INFO, result.getThrowable());
		Reporter.log(testName + "--------EXECUTION FAILED");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(
				"./errorShots/" + testName + LocalDateTime.now().toString().replace(":", "_") + ".png");
		try {
			FileUtils.copyFile(src, dest);
			test.addScreenCaptureFromPath(dest.getAbsolutePath());
		} catch (Exception e) {

		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(testName + "----------EXECUTION SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("./Extent Reports/"+LocalDateTime.now().toString().replace(':', '_')+".html");
		htmlReport.config().setDocumentTitle("Bhim Document");
		htmlReport.config().setReportName("Bhim Extent Report");
		htmlReport.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("Platform Name", "Android");
		report.setSystemInfo("Device Name", "OnePlus 7 Pro");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
