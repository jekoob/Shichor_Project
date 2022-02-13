package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetaildDestinationPage extends NavPage {
	@FindBy(css = ".destination-description>.destination-description__title")
	WebElement titledescription;
	public DetaildDestinationPage(WebDriver driver) {
		super(driver);
	}

	public boolean isValiDetailedDestinationPage(String title) {
		wait.until(ExpectedConditions.visibilityOf(titledescription));
		return validationTitleTab(titledescription.getText());
	}
}
