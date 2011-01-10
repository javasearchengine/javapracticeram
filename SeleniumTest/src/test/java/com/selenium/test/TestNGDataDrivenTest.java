package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.BrowserConfigurationOptions;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class TestNGDataDrivenTest {

	static SeleniumServer server = null;
	static Selenium selenium = null;
	Properties props = null;

	@BeforeClass
	public void setUpServer() {

		props = new Properties();

		try {

			InputStream in = getClass().getResourceAsStream(
					"settings.properties");

			props.load(in);

		} catch (Exception e) {
			System.out.println("Could not read settings.properties files :");
			e.printStackTrace();
		}

		RemoteControlConfiguration rcc = new RemoteControlConfiguration();

		rcc.setFirefoxProfileTemplate(new File(props
				.getProperty("firefox.profile.location")));
		try {

			server = new SeleniumServer(rcc);
			server.start();

			selenium = new DefaultSelenium(props.getProperty("hostname"),
					(Integer.parseInt(props.getProperty("portNumber"))),
					props.getProperty("browser"), props.getProperty("baseUrl"));

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

	@DataProvider(name = "DataSource")
	public Object[][] dataProvider() {

		Object[][] returnObject = new Object[][]{{"Ramesh"},{"Rajesh"}};

		return returnObject;
	}

	@Test(dataProvider="DataSource")
	public void wikiTest(String name) {

//		System.out.println(name);

	}

}
