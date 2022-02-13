package objects;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import objects.BasePage;

public class DetailedItemPage extends BasePage {

	@FindBy(css = "#__next .trip-heading__heading")
	WebElement ItemTitle;
	@FindBy(css = "#__next .loadable-content__load-more .btn-small")
	WebElement viewBtn;
	public DetailedItemPage(WebDriver driver) {
		super(driver);
	}

	public String getItemTitle() {
		wait.until(ExpectedConditions.visibilityOf(ItemTitle));
		waiting(10000);
		// System.out.println(ItemTitle.getText());
//		waitForVisibilityOf(ItemTitle);
//		System.out.println(ItemTitle);
		return ItemTitle.getText();
	}

	public void scrollToViewBtn() {
		scrollDown(viewBtn);
		waitForVisibilityOf(viewBtn);
		click(viewBtn);
	}

	public void linkToBooking() {
		scrollDown(viewBtn);
		waitForVisibilityOf(viewBtn);
		click(viewBtn);
	}
}
