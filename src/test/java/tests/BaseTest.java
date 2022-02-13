package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
public WebDriver driver;
	@BeforeClass
	public void setup() {
		driver = WebDriverManager.chromedriver().create();

		driver.manage().window().maximize();
		driver.get("https://www.shichor.co.il/en");
	}
//	@BeforeClass 
//	public void bfc02_check_The_Language() {
//		NavPage np=new NavPage(driver);
//	//np.set_Language();
//	}
	@AfterClass
	public void tearDown() {
		//close browser
				driver.quit();
	}
}
