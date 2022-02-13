package objects;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	String mainWindow;
	Actions action; 
	JavascriptExecutor js;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	void fillText(WebElement el, String text) {
		highlightElement(el, "green", "yellow");
		el.clear();
		waiting(1000);
		el.sendKeys(text);
	}

	void click(WebElement el) {
		highlightElement(el, "green", "yellow");
		// WRITE TO LOG
//		waitForVisibilityOf(el);
	//	wait.until(ExpectedConditions.elementToBeClickable(el));
		el.click();
	}

	String getText(WebElement el) {
		highlightFontElement(el, "red");
		highlightElement(el, "black", "orange");
		return el.getText();
	}

	void selectByValue(WebElement el, String value) {
		Select s = new Select(el);
		s.selectByValue(value);
	}

	String getTitle() {
		highlightElement(driver.findElement(By.cssSelector("title")),"black","yellow");
		return driver.getTitle();
	}

	// Alert
	void alertOK(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}

	void alertOK() {
		driver.switchTo().alert().accept();
	}

	void alertCancel() {
		driver.switchTo().alert().dismiss();
	}

	// Mouse
	void dragAndDrop(WebElement src, WebElement target) {
		highlightElement(src, "yellow", "orange");
		highlightElement(target, "blue", "orange");
		action.moveToElement(src).clickAndHold().build().perform();
		waiting(1000);
		action.moveToElement(target).release().build().perform();

	}

	// windows
	public void moveToNewWindow() {
		mainWindow = driver.getWindowHandle();
		Set<String> list = driver.getWindowHandles();
		for (String win : list) {
			System.out.println(win);
			driver.switchTo().window(win);
		}
	}

	public void moveToMainWindow() {
		driver.close();
		driver.switchTo().window(mainWindow);
	}

	// Wait
	void waitForVisibilityOf(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	void waitForInVisibilityOf(WebElement el) {
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

	public void waiting(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	private void highlightFontElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "font-color:" + color + "; " + originalStyle;
//		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);
	}
	private void highlightElement(WebElement element, String color, String background) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:" + background + "; border: 1px solid " + color + ";" + originalStyle;
//		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}

	// Scroll Down on page
	public void scrollDown(WebElement element) {
		System.out.println(element.getLocation().getY());
		for (int i = 0; i < element.getLocation().getY(); i += 20) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + i + ")");
		}
	}

	// Validation
	public boolean validationTitleTab(String titleTab) {
		wait.until(ExpectedConditions.titleContains(titleTab));
		String title = getTitle();
		System.out.println(title);
		boolean a = title.contains(titleTab) ? true : false;
		return a;
	}

}
