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
	public Map<String, String> dataProvider() {
		Map<String, String> testData = TestNGDataDrivenTest.readExcelData(
				props.getProperty("excelName"), props.getProperty("sheetName"));
		return testData;
	}

	public static Map<String, String> readExcelData(String fileName,
			String sheetName) {
		Map<String, String> testData = new HashMap<String, String>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					"/home/ram/test.xls"));
			HSSFSheet sheet = wb.getSheet("TestSheet");

			for (int i = 2; i < 5; i++) {
				HSSFRow currRow = sheet.getRow(i);
				testData.put(currRow.getCell(0).getStringCellValue().trim(),
						currRow.getCell(1).getStringCellValue().trim());
			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		return testData;

	}

	@Test(dataProvider = "DataSource")
	public void wikiTest(Map<String, String> testData) {

		
		for(int i=0;i<testData.size();i++){
			System.out.println("Seacrch string is "+ testData.get("Ricky Ponting"));
		}


		selenium.setTimeout("100000");
		selenium.setSpeed("2000"); // This command is to control the speed of
									// selenium commands
		selenium.open("/");
//		System.out.println("First Value " + );

	}

}
