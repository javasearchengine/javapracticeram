package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
	public String[] dataProvider() {
		String[] names = TestNGDataDrivenTest.readExcelData(
				props.getProperty("excelName"), props.getProperty("sheetName"));
		return names;
	}

	public static String[]  readExcelData(String fileName, String sheetName) {
//
//		InputStream in = getClass().getResourceAsStream(fileName);
		String[] names = null;

		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("/home/ram/test.xls"));
			HSSFSheet sheet = wb.getSheet("TestSheet");

			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				HSSFSheet sheet = wb.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
						+ " row(s).");
				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}

					int cells = row.getPhysicalNumberOfCells();
					System.out.println("\nROW " + row.getRowNum() + " has " + cells
							+ " cell(s).");
					for (int c = 0; c < cells; c++) {
						HSSFCell cell = row.getCell(c);
						String value = null;

						switch (cell.getCellType()) {

							case HSSFCell.CELL_TYPE_FORMULA:
								value = "FORMULA value=" + cell.getCellFormula();
								break;

							case HSSFCell.CELL_TYPE_NUMERIC:
								value = "NUMERIC value=" + cell.getNumericCellValue();
								break;

							case HSSFCell.CELL_TYPE_STRING:
								value = "STRING value=" + cell.getStringCellValue();
								break;

							default:
						}
						System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
								+ value);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}

		return names;

	}

	@Test(dataProvider = "DataSource")
	public void wikiTest(String names) {

		selenium.setTimeout("100000");
		selenium.setSpeed("2000"); // This command is to control the speed of
									// selenium commands
		selenium.open("/");
		System.out.println("First Value " + names);

	}

}
