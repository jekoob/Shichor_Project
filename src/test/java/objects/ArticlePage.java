package objects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ArticlePage extends NavPage {
	@FindBy(css = ".article-page__share .share-buttons ")
	WebElement share_buttons;
	@FindBy(css = ".article-page__share .share-buttons button")
	List<WebElement> socialBtns;
	@FindBy(css = ".destinations-page-services.row li a")
	List<WebElement> servicesBtns;

	public ArticlePage(WebDriver driver) {
		super(driver);
	}

	public void scroll_to_buttom() {
		wait.until(ExpectedConditions.visibilityOf(share_buttons));
		scrollDown(share_buttons);
	}

	public void check_social_links() {
		int counter = 1;
		ArrayList<String> allwindows = new ArrayList<String>();
		// ArrayList<String> allwindows = new
		// ArrayList<String>(driver.getWindowHandles());
		for (WebElement el : socialBtns) {

			if (counter < socialBtns.size()) {
				String expected = el.getAttribute("aria-label").toLowerCase();
				click(el);
				// System.out.println(expected);
				allwindows.addAll(driver.getWindowHandles());
				driver.switchTo().window(allwindows.get(1));
				String actual = getTitle().toLowerCase();
				driver.close();
				// System.out.println(actual);
				if (expected.length() == actual.length()) {
					Assert.assertTrue(actual.equals(expected));
				} else {
					Assert.assertTrue(actual.contains(expected));
				}
				driver.switchTo().window(allwindows.get(0));
				// System.out.println(counter);
				counter++;
				allwindows = new ArrayList<String>();
			} else {
				break;
			}
		}
	}

	public void check_services_links() {
		int counter = 0;
		for (WebElement el : servicesBtns) {
			String expected = el.getAttribute("href");
			if (counter == 3) {
				expected = expected.substring(0, 28);
			}
			System.out.println(expected);
			click(el);
			ArrayList<String> allwindows = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(allwindows.get(1));

			String actual = driver.getCurrentUrl();
			if (expected.length() == actual.length()) {
				System.out.println(expected);
				System.out.println(actual);
				Assert.assertTrue(actual.equals(expected));
			} else {
				System.out.println(expected);
				System.out.println(actual);
				Assert.assertTrue(actual.contains(expected));

			}
			driver.close();
			driver.switchTo().window(allwindows.get(0));
			counter++;
		}
	}
}
