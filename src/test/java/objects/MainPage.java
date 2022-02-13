package objects;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MainPage extends SearchBarPage {

	@FindBy(css = "#__next main [role='button']")
	List<WebElement> recommended_list;
	@FindBy(css = "#__next .btn-secondary.cookies-policy-banner__button.btn")
	WebElement gotItBtn;


	public MainPage(WebDriver driver) {
		super(driver);

	}

	public void got_It() {
		waitForVisibilityOf(gotItBtn);
		click(gotItBtn);
	}

	public String choose_Recommended_Va(int num) {
		String title = recommended_list.get(num).findElement(By.cssSelector(".trip-card__name")).getText();
		click(recommended_list.get(num));
		return (title);
	}


}
