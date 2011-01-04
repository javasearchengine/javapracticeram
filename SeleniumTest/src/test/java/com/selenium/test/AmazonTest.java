package com.selenium.test;

import java.io.File;

import javax.xml.datatype.Duration;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.BrowserConfigurationOptions;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.condition.Condition;
import com.thoughtworks.selenium.condition.ConditionRunner.Context;

public class AmazonTest {

	static SeleniumServer server = null;
	static Selenium selenium = null;

	@BeforeClass
	public void setUpServer() {

		RemoteControlConfiguration rcc = new RemoteControlConfiguration();
		rcc.setFirefoxProfileTemplate(new File(
				"/home/ram/.mozilla/firefox/bbgl2483.selenium/"));
		try {

			server = new SeleniumServer(rcc);
			server.start();

			selenium = new DefaultSelenium("localhost", 4444, "*chrome",
					"http://www.amazon.com");

			BrowserConfigurationOptions browserOptions = new BrowserConfigurationOptions();
			browserOptions.setSingleWindow();
			selenium.start(browserOptions);

		} catch (Exception e) {
			System.out.println("Setup failed" + e.getMessage());
		}

	}

	@AfterClass
	public void cleanUp() {

		selenium.stop();
		server.stop();

	}

	@Test
	public void testSeleniumBasic() {

		selenium.setTimeout("60000");
//		selenium.setSpeed("2000");
		selenium.open("/");

		selenium.click("//a[text()=\"Start here\"]");
		selenium.waitForPageToLoad("20000");
		selenium.captureScreenshot("/home/ram/workspace/SeleniumTest/screenshot.png");
		
		String mailString = this.randomString();
		
		selenium.type("//input[@id=\"ap_email\"][@type=\"email\"]", mailString+"@mailinator.com");
		
		selenium.click("//input[@id='ap_signin_create_radio'][@type=\"radio\"][@value=\"1\"]");
		
		selenium.click("//input[@id=\"signInSubmit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.captureScreenshot("/home/ram/workspace/SeleniumTest/screenshot.png");
		
		
		
		
		selenium.click("id=navSaMenuItem_12");
		selenium.click("link=Automotive Parts*");
		
		selenium.waitForPageToLoad("30000");

		selenium.clickAt("id=csld-next-button","0,0");
		selenium.clickAt("id=csld-next-button","0,0");
		
		String pageNumber = selenium.getText("id=csld-current-page");
		
		int currentPage = Integer.parseInt(pageNumber);
		
		Assert.assertEquals(3, currentPage);
		
		System.out.println("Current page number is"+currentPage);

		selenium.type("id=twotabsearchtextbox", "mask");
		selenium.click("//input[@type='image'][@alt='Go']");
		selenium.waitForPageToLoad("10000");
		selenium.captureScreenshot("/home/ram/workspace/SeleniumTest/screenshot.png");
		
		selenium.select("id=searchDropdownBox", "label=Books");
		selenium.captureScreenshot("/home/ram/workspace/SeleniumTest/screenshot.png");

	}
	
	public static String randomString(){
		
		final int LENGTH = 6;
		StringBuffer buff = new StringBuffer();
		for(int x=0;x < LENGTH; x++){
			
			buff.append((char)((int)(Math.random()*26)+97)); 
		}
		return buff.toString();
		
		
	}

}
