package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MagazinPage extends NavPage {
	@FindBy(css = ".container .articles-page__title")
	WebElement title_page;
	@FindBy(css = ".articles-list .articles-list__mansory a")
	List<WebElement> articles_list;

	
	public MagazinPage(WebDriver driver) {
		super(driver);
	}

	public void clickOn_artical(String artical_title) {
		wait.until(ExpectedConditions.visibilityOfAllElements(articles_list));
	for (WebElement el : articles_list) {
			if(artical_title.equalsIgnoreCase(el.findElement(By.cssSelector(".article-card__title")).getText())) {
				waiting(1000);
				click(el);
				Assert.assertEquals(el.findElement(By.cssSelector(".article-card__title")).getText(), artical_title);
				break;
			}
		}
	}


	// validation
	public boolean check_titlePage(String title) {
		wait.until(ExpectedConditions.visibilityOf(title_page));
		return getText(title_page).toLowerCase().contains(title.toLowerCase()) ? true : false;
	}
}
