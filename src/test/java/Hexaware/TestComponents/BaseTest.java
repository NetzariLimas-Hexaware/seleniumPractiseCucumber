package Hexaware.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Hexaware.pageobjects.ListingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	
	public WebDriver initializeApplication() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Hexaware/resources/GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public ListingPage runApplication() throws IOException {
		initializeApplication();
		ListingPage listingPage = new ListingPage(driver);
		listingPage.goTo();
		return listingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		//driver.quit();
	}
	
	public String[] splitString(String text, String parameter) {
		return text.split(parameter);
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fs = new File(filePath);
		FileUtils.copyFile(source, fs);
		return filePath;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

		// Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
}
