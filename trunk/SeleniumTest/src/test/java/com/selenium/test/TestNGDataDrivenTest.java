package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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

		Object[][] testData = readExcelData(props.getProperty("sheetName"),
				props.getProperty("excel.path"),
				props.getProperty("table.name"));

		return testData;

	}

	public String[][] readExcelData(String sheetName, String filePath,
			String tableName) {
		String[][] testData = null;

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					filePath));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			HSSFCell[] boundaryCells = findCell(sheet, tableName);
			HSSFCell startCell = boundaryCells[0];
			HSSFCell endCell = boundaryCells[1];
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow - 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					testData[i - startRow][j - startCol] = sheet.getRow(i)
							.getCell(j).getStringCellValue();

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		return testData;

	}

	public static HSSFCell[] findCell(HSSFSheet sheet, String text) {

		String pos = "start";

		HSSFCell[] cells = new HSSFCell[2];

		for (Row row : sheet) {
			for (Cell cell : row) {
				if (text.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("start")) {
						cells[0] = (HSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (HSSFCell) cell;
					}
				}

			}
		}
		return cells;
	}

	@Test(dataProvider = "DataSource")
	public void wikiTest(String searchKeyword, String DOB) {

		System.out.println("Search Keyword is " + searchKeyword);
		System.out.println("DOB is " + DOB);

		selenium.setTimeout("100000");
		selenium.setSpeed("2000"); // This command is to control the speed of
									// selenium commands
		selenium.open("/");
		// System.out.println("First Value " + );

	}

}