package tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.eventbus.DeadEvent;

import objects.BookingPage;
import objects.DetailedItemPage;
import objects.MainPage;

public class RecommendedListTest extends BaseTest {

	@Test
	//choose recommended destination
	public void tc01_select_Recommended_Va() {
		MainPage mp = new MainPage(driver);
		// mp.choose_Recommended_Va(2);
		mp.got_It();
		String expected = mp.choose_Recommended_Va(2);
		DetailedItemPage dip = new DetailedItemPage(driver);
		String actual = dip.getItemTitle();
		Assert.assertEquals(actual, expected);
		 System.out.println(expected);
		// driver.navigate().back();
	}
	
	@Test
	//scroll down to element
	public void tc02_ScrollDown() {
		DetailedItemPage dip = new DetailedItemPage(driver);
		dip.scrollToViewBtn();
	}
	
	@Test
	//handle booking link
	public void tc03_Handaling_Booking_Page() {
		DetailedItemPage dip = new DetailedItemPage(driver);
		dip.linkToBooking();
		BookingPage bp = new BookingPage(driver);
		bp.moveToNewWindow();
		Assert.assertTrue(bp.isBookingPage());
		bp.moveToMainWindow();
	}
	
}
