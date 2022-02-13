package objects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchBarPage extends NavPage {

	public SearchBarPage(WebDriver driver) {
		super(driver);

	}

	// where option
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(1) button")
	WebElement whereBtn;

	// for destination container ;
	@FindBy(css = ".search-direction__row div:nth-child(3)  .rs-select__input>input")
	WebElement destanitionFeild;
	@FindBy(css = ".rs-select__menu-list .rs-select__option>.search-direction-option")
	List<WebElement> result_list;
	@FindBy(css = ".rs-select__menu-notice--loading")
	WebElement loading;

	// for when option menu search
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(2) button")
	WebElement whenBtn;
	// predefined way
	@FindBy(css = ".search-date-periods button")
	List<WebElement> date_periodsBtn;
	@FindBy(css = ".slider-wrapper ")
	WebElement slider_wrapper;
	// manually way
	@FindBy(css = ".flatpickr-months .flatpickr-month:nth-child(2) .numInputWrapper span.arrowUp")
	WebElement arrowUp;
	@FindBy(css = ".flatpickr-months .flatpickr-month .cur-month")
	List<WebElement> current_month;
	@FindBy(css = " .flatpickr-next-month")
	WebElement next_month;
	@FindBy(css = ".search-dates__datepicker .dayContainer:nth-child(1) span")
	List<WebElement> left_dayContainer;
	@FindBy(css = ".search-dates__datepicker .dayContainer:nth-child(2) span")
	List<WebElement> right_dayContainer;

	// for why option menu search
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(4) button")
	WebElement whyBtn;
	@FindBy(css = "ul.search-purpose__list>li")
	List<WebElement> purpose_List;

	// for what option menu search
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(6) button")
	WebElement whatBtn;
	@FindBy(css = ".search-interests__form .interests-list>li")
	List<WebElement> interest_li_item;

	// for who option menu search
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(3) button")
	WebElement whoBtn;
	@FindBy(css = "	.parties-input__section--child .parties-input__fieldset>div")
	List<WebElement> section_child_fieldsets;
	@FindBy(css = "	.parties-input__section--adult .parties-input__fieldset>div")
	List<WebElement> section_adult_fieldsets;
	@FindBy(css = ".Toastify .Toastify__toast--error")
	WebElement limit_error;

	// for how much option menu search
	@FindBy(css = ".search-budget-standards .search-budget-standards__button ")
	List<WebElement> financialBtns;
	@FindBy(css = "div>.search-bar__container .search-bar__steps div:nth-child(5) button")
	WebElement howMuchBtn;

	// finds location
	public String fill_where_location(String location) {
		click(whereBtn);
		wait.until(ExpectedConditions.visibilityOf(destanitionFeild));
		fillText(destanitionFeild, location);
		wait.until(ExpectedConditions.visibilityOf(loading));
		wait.until(ExpectedConditions.invisibilityOf(loading));
		for (WebElement el : result_list) {
			System.out.println(el.findElement(By.tagName("span")).getText());
			if (el.findElement(By.tagName("span")).getText().toLowerCase().contains(location.toLowerCase())) {
				String selectedDestination = el.findElement(By.tagName("span")).getText().toLowerCase();
				action.moveToElement(el).build().perform();
				waiting(500);
				click(el);
				destanitionFeild.sendKeys(Keys.RETURN);
				return selectedDestination;
			}
		}
		return null;
	}

	// set when to fly
	public void set_date_flight_predefined(String when, int nightsNum) {
		click(whenBtn);
		String s = when;
		switch (s) {
		case "next two weeks":
			click(wait.until(ExpectedConditions.elementToBeClickable(date_periodsBtn.get(0))));
			break;
		case "next month":
			click(wait.until(ExpectedConditions.elementToBeClickable(date_periodsBtn.get(1))));
			break;
		case "next three months":
			click(wait.until(ExpectedConditions.elementToBeClickable(date_periodsBtn.get(2))));
			break;
		}

		action.moveToElement(slider_wrapper.findElement(By.cssSelector(".rc-slider-handle"))).click().build().perform();

		if (nightsNum > 4) {
			for (int i = 0; i < nightsNum - 4; i++) {
				slider_wrapper.findElement(By.cssSelector(".rc-slider-handle")).sendKeys(Keys.ARROW_UP);
			}
		} else {
			for (int i = 0; i < 4 - nightsNum; i++) {
				slider_wrapper.findElement(By.cssSelector(".rc-slider-handle")).sendKeys(Keys.ARROW_DOWN);
			}
		}

	}

	public void set_flight_munually() {


		Date date = new Date();
	    int desireDay;
	    int desireMonth;
	    int desireYear=date.setDesireYear();
	 
		do {
			desireDay=date.setDesireDay();
			desireMonth=date.setDesireMonth();
			System.out.println(desireDay);
			System.out.println(desireMonth);
		}while(desireDay<=date.getCurrentDay() || desireMonth<=date.getCurrentMonth());
		
	    click(whenBtn);
	    waiting(600);
	    for(int i=date.getCurrentYear();i<desireYear;i++) {
	    	   waiting(600);
	    	click(arrowUp);
	    }
	    
	    boolean state=true;
	    int counter=0;
	    do {
	    	for (WebElement el : current_month) {
	    		
				if(el.getText().equalsIgnoreCase(date.getMonths()[desireMonth-1])) {
						state=false;
					}
				if(!el.getText().equalsIgnoreCase(date.getMonths()[desireMonth-1])) {
						counter++;
					}
				if(counter==2) {
					waiting(600);
					click(next_month);
					counter=0;
				}
			}
	    }while(state);
	    
		
		 counter=0; //find the desire month and click on the desire day
		 int to= 0;
		 List<WebElement> currentContainer= null,currentContainer1 =  null;
	    for (WebElement el : current_month) {
    		
			if(el.getText().equalsIgnoreCase(date.getMonths()[desireMonth-1])) {
					switch(counter) {
					case 0:
						currentContainer=left_dayContainer;
						break;
					case 1:
						currentContainer = right_dayContainer;
						break;
					}
					for (WebElement day : currentContainer) {
						if(day.getText().equalsIgnoreCase(String.valueOf(desireDay))) {
							action.moveToElement(day).build().perform();
							waiting(1000);
							click(day);
							System.out.println("wow");
							break;
						}
					}
					if(desireDay+date.getVacation_length()<=date.getMonthSize()) {
						currentContainer=left_dayContainer; // because after click the right container pop to left(might be bug:functionality)
						 to =desireDay+date.getVacation_length();
					}
					else {
						currentContainer=right_dayContainer;
						to = date.getVacation_length()-(date.getMonthSize()-desireDay);
					}
					for (WebElement day : currentContainer) {
							if(day.getText().equalsIgnoreCase(String.valueOf(to))) {
								action.moveToElement(day).build().perform();
								waiting(1000);
								click(day);
								System.out.println("wow");
								
								break;
							}
						}
					break;	
					}

	    	else {
	    		counter++;
	    	}
	  }
	}


	public boolean set_reason_flight(String purpose) {
		click(whyBtn);
		for (WebElement el : purpose_List) {
			if (el.findElement(By.tagName("span")).getText().equalsIgnoreCase(purpose)) {
				click(el);
			}
		}
		if (whyBtn.findElement(By.cssSelector("span:nth-child(2)")).getText().equalsIgnoreCase(purpose)) {
			return true;
		} else {
			return false;
		}
	}

	public void set_interests(int gallary, int Attrac, int show, int gastro, int shop, int night, int recreation,
			int sport) {
		click(whatBtn);
		List<Integer> list = Arrays.asList(gallary, Attrac, show, gastro, shop, night, recreation, sport);
		Point location;
		int counter = 0, slider_rail_width, expected, actual;
		for (WebElement el : interest_li_item) {
			location = el.findElement(By.cssSelector(".interest-slider__handle ")).getLocation();
			slider_rail_width = el.findElement(By.cssSelector(".rc-slider-rail ")).getSize().width;
			action.clickAndHold(el.findElement(By.cssSelector(".interest-slider__handle ")))
					.pause(Duration.ofMillis(400)).build().perform();
			action.moveByOffset(slider_rail_width / 10 * list.get(counter) - 6, location.getY()).build().perform();
			action.release().build().perform();

			// validation
			expected = list.get(counter);
			actual = Integer.parseInt(el.findElement(By.cssSelector(".rc-slider-label>span")).getText());
			Assert.assertEquals(actual, expected);
			counter++;
		}

	}

	public void set_who_is_come(List<Integer> ages) {
		click(whoBtn);
		int adult = 2;// because there is a default by site.
		int child = 0;
		for (Integer person : ages) {

			if (person >= 18 && adult + child < 9) { // adults section of field sets
				if (person >= 18 && person <= 30) {
					click(section_adult_fieldsets.get(0).findElement(By.cssSelector("button:last-child")));
					adult++;
				}
				if (person >= 31 && person <= 45) {
					click(section_adult_fieldsets.get(1).findElement(By.cssSelector("button:last-child")));
					adult++;
				}
				if (person >= 46 && person <= 65) {
					click(section_adult_fieldsets.get(2).findElement(By.cssSelector("button:last-child")));
				}
				if (person >= 65) {
					click(section_adult_fieldsets.get(3).findElement(By.cssSelector("button:last-child")));
					adult++;
				}
			}

			if (person < 18 && adult + child < 9) { // kids section of field sets
				if (person > 0 && person <= 3) { // first field
					click(section_child_fieldsets.get(0).findElement(By.cssSelector("button:last-child")));
					child++;
				}
				if (person >= 4 && person <= 8) { // second field
					click(section_child_fieldsets.get(1).findElement(By.cssSelector("button:last-child")));
					child++;
				}
				if (person >= 9 && person <= 13) { // third field
					click(section_child_fieldsets.get(2).findElement(By.cssSelector("button:last-child")));
					child++;
				}
				if (person >= 14 && person <= 17) { // fourth field
					click(section_child_fieldsets.get(3).findElement(By.cssSelector("button:last-child")));
					child++;
				}
			}
		}
		// validation
		String expected = adult + " adults, " + child + " kids";
		String actual = whoBtn.findElement(By.cssSelector(".search-bar__item-value")).getText();
		Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase());

		if (ages.size() > 7) {
			String expected_error_message = "The total number of participants shouldn`t be more than 9";
			String actual_error_message = wait.until(ExpectedConditions.visibilityOf(limit_error)).getText();
			Assert.assertEquals(actual_error_message.toLowerCase(), expected_error_message.toLowerCase());
			click(wait
					.until(ExpectedConditions.elementToBeClickable(limit_error.findElement(By.cssSelector("button")))));
		}
	}

	public boolean set_how_much(String financial_standard) {
		click(howMuchBtn);

		for (WebElement el : financialBtns) {
			if (el.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(financial_standard)) {

				click(el);
			}
		}
		if (howMuchBtn.findElement(By.cssSelector("span:nth-child(2)")).getText()
				.equalsIgnoreCase(financial_standard)) {
			return true;
		} else {
			return false;
		}

	}

}
