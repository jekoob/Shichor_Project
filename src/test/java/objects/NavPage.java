package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class NavPage extends BasePage {
	@FindBy(css = ".app-header__container>a:first-child")
	WebElement homeLink;
	@FindBy(css = ".app-header__row>nav>a:nth-child(1)")
	WebElement distantionLink;
	@FindBy(css = ".app-header__row>nav>a:nth-child(2)")
	WebElement magazinLink;
	@FindBy(css = ".app-header__row>nav>a:nth-child(3)")
	WebElement aboutLink;

	// for sign-in section
	@FindBy(css = ".app-header__row>nav>button")
	WebElement sign_in_btn;
	@FindBy(css = ".sign-in-form .sign-in-form__footer>a:last-child")
	WebElement sign_up_btn;
	@FindBy(css = "[id='name']")
	WebElement first_name_input;
	@FindBy(css = "[id='surname']")
	WebElement last_name_input;
	@FindBy(css = "[id='email']")
	WebElement email_input;
	@FindBy(css = "[id='password']")
	WebElement password_input;
	@FindBy(css = ".form-field.error")
	List<WebElement> error_fields;
	@FindBy(css = ".sign-up-form button:nth-child(3)")
	WebElement eye_pass_btn;
	@FindBy(css = ".modal-v2__container button[type='submit']")
	WebElement create_Btn;
	@FindBy(css = "div.modal-v2__container button.modal-v2__close-button")
	WebElement close_btn;          
	@FindBy(css = " .sign-up-form__checks .point__checkmark")
	List<WebElement> checkBoxes;
	// language switcher
	@FindBy(css = "header .lang-switcher")
	WebElement lang_switcher;
	@FindBy(css = ".lang-switcher button")
	List<WebElement> nav_dropdown_menu;
	@FindBy(css = "header .lang-switcher .nav__dropdown-menu>button")
	List<WebElement> nav_items;

	public NavPage(WebDriver driver) {
		super(driver);
	}

	public void check_boundaries(String firstName, String lastName, String email, String password) { // must fail
		waiting(1000);
		click(sign_in_btn);
		click(sign_up_btn);
		
		for (int i=0;i<4;i++) {
			System.out.println(first_name_input.getAttribute("type") + " || line : 56");
				switch (i) {
				case 0:
					fillText(first_name_input, firstName);
					click(create_Btn);
					char[] chars = firstName.toCharArray();
					for (char c : chars) {
						// check id there is a number in the string
						if (Character.isDigit(c)) {

							// check that after submit there are error-message at other input fields
							for (int j = 1; j < error_fields.size(); j++) {
								wait.until(ExpectedConditions.visibilityOf(error_fields.get(j)));
								String expected_error = getText(error_fields.get(j));
								Assert.assertTrue(expected_error.equalsIgnoreCase("Required"));
							}
							break;
						}

					}
					break;

				case 1:
					fillText(last_name_input, lastName);
					click(create_Btn);
					chars = lastName.toCharArray();
					String expected_error;
					for (char c : chars) {
						// check id there is a number in the string
						if (Character.isDigit(c)) {

							// check that after submit there are error-message at other input fields
							for (int j = 2; j< error_fields.size(); j++) {
								expected_error = getText(error_fields.get(j));
								Assert.assertTrue(expected_error.equalsIgnoreCase("Required"));
							}
							break;
						}

					}
					break;
				case 2:
					fillText(email_input, email);
					click(create_Btn);
						for (int j = 2; j < error_fields.size(); j++) {
							if(j==2) {
								expected_error = error_fields.get(j).getText();
								Assert.assertTrue(expected_error.equalsIgnoreCase("Please enter a valid email address"));
							}
							if(j==3) {
								expected_error = error_fields.get(j).getText();
								Assert.assertTrue(expected_error.equalsIgnoreCase("Required"));
							}
							
					}
					break;

				case 3:
					fillText(password_input, password);
					click(create_Btn);
					click(eye_pass_btn);
					expected_error = getText(error_fields.get(2));
					System.out.println(expected_error + "line : 111");
					Assert.assertTrue(error_fields.get(2).getText().equalsIgnoreCase(expected_error));
					fillText(email_input, "yakov.sachuk@gmail.com");
					click(create_Btn);
					waiting(1000);
					for (int j = 0; j < 4; j++) {
						

						switch (j) {
						case 0:
							System.out.println(expected_error + "|| line : 120");
							expected_error = getText(error_fields.get(0));
							Assert.assertTrue(expected_error.equalsIgnoreCase("This field should not contain digits"));
							break;

						case 1:
							expected_error = getText(error_fields.get(1));
							Assert.assertTrue(expected_error.equalsIgnoreCase("This field should not contain digits"));
							break;

						case 3:
							expected_error = getText(error_fields.get(3));
							Assert.assertTrue(expected_error
									.equalsIgnoreCase("This password must contain lower and upper case letters"));
							break;
						}
					}
				} // closing the switch
			}
		click(close_btn);
	}

	public void sign_up(String firstName, String lastName, String email, String password) {
		//waiting(2000);
		wait.until(ExpectedConditions.visibilityOf(sign_in_btn));
		click(sign_in_btn);
		click(sign_up_btn);
		for (int i=0;i<4;i++) {
				switch (i) {
						case 0: {
							fillText(first_name_input, firstName);
							break;
						}
						case 1: {
							fillText(last_name_input, lastName);
							break;
						}
						case 2: {
							fillText(email_input, email);
							break;
						}
						case 3: {
							if (!password_input.getAttribute("type").equalsIgnoreCase("password")) {
								click(eye_pass_btn);
							}
							fillText(password_input, password);
							break;
						}
				}
			}
			for(int i=0;i< checkBoxes.size();i++) {
				checkBoxes.get(i).click();
				}
			click(create_Btn);
			wait.until(ExpectedConditions.visibilityOf(error_fields.get(2)));
			System.out.println(error_fields.get(2).getText()+" || line 190");
			if (getText(error_fields.get(2)).equalsIgnoreCase("A user is already registered with this e-mail address.")) {
				System.out.println(error_fields.get(2).getText()+" || line 192");
				waiting(1000);
				click(close_btn);
			}
	}
	

	public void login() {
		click(sign_in_btn);
		fillText(driver.findElement(By.cssSelector("[id='email']")), "yakov.sachuk@gmail.com");
		fillText(driver.findElement(By.cssSelector("[id='password']")), "19Shichor48");
		click(driver.findElement(By.cssSelector(".modal-v2__container [type='submit']")));
		waiting(1000);
	}

	public void openDestinationPage() {
		click(distantionLink);
		waiting(2000);
	}

	public void openMagazinPage() {
		click(magazinLink);
	}

	public void openAboutPage() {
		click(aboutLink);
	}

	public void click_on_homeLogo() {
		click(homeLink);
	}

	public void set_Language() {
		wait.until(ExpectedConditions.visibilityOf(nav_dropdown_menu.get(0)));
		action.moveToElement(nav_dropdown_menu.get(0)).build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElements(nav_dropdown_menu));
		for (WebElement el : nav_dropdown_menu) {
			if (el.getText().equalsIgnoreCase("english")) {
				click(el);
				Point location  = el.getLocation();
				
			}
		}
	}

	// validation
	public boolean isValidDestinationPage(String title) {
		return validationTitleTab(title);
	}

}
