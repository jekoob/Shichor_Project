package objects;

import org.openqa.selenium.WebDriver;

public class BookingPage extends BasePage {

	public BookingPage(WebDriver driver) {
		super(driver);
	}

	public boolean isBookingPage() {
		String title = getTitle();
		System.out.println(title);
		boolean a = title.contains("Booking.com") ? true : false;
		return a;
	}
}
