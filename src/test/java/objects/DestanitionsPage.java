package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DestanitionsPage extends NavPage {

	@FindBy(css = "main .form-input input")
	WebElement searchFeild;
	@FindBy(css = "main form button.destinations-header__btn")
	WebElement searchBtn;
	@FindBy(css = "ul.destinations-page-list>li")
	List<WebElement> popularList;

	public DestanitionsPage(WebDriver driver) {
		super(driver);

	}

	public void search_window(String Destination) {
		fillText(searchFeild, Destination);
	}

	// choose item by title
	public void popularItemByTitle(String title) {
		wait.until(ExpectedConditions.visibilityOfAllElements(popularList));
		for (WebElement el : popularList) {
//			System.out.println(getText(el.findElement(By.cssSelector(".destinations-page-item__title"))));
			if (getText(el.findElement(By.cssSelector(".destinations-page-item__title"))).equalsIgnoreCase(title)) {
				click(el);
				System.out.println(popularList.size());
				break;
			}
		}
	}

	public String popularItemByIndex(int num) {
		wait.until(ExpectedConditions.visibilityOfAllElements(popularList));
		String title = popularList.get(num).findElement(By.cssSelector(".destinations-page-item__title")).getText();
		System.out.println(popularList.size());
		click(popularList.get(num).findElement(By.cssSelector("a")));
		return title;
	}
}
