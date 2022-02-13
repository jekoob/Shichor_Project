package tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import objects.DetailedItemPage;
import objects.MainPage;

public class SearchBarTest extends BaseTest{
	@Test
	public void tc01_what() {
		MainPage mp = new MainPage(driver);
		mp.got_It();
		List<Integer> list =Arrays.asList(4,2,6,4,9,6,2,8);
		mp.set_interests(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7));
	}
	@Test
	public void tc02_howMuch() {
		MainPage mp = new MainPage(driver);
		Assert.assertTrue(mp.set_how_much("luxury"));
	}
	@Test
	public void tc03_why() {
		MainPage mp = new MainPage(driver);
		Assert.assertTrue(mp.set_reason_flight("Family trip"));
	}

	@Test
	public void tc04_who() {
		MainPage mp = new MainPage(driver);
		List<Integer> personsList= Arrays.asList(19,1,10,13,15,21,60,70,10,11,2);
		mp.set_who_is_come(personsList);
	}
	@Test
	public void tc05_when_predefined() {
		MainPage mp = new MainPage(driver);
		mp.set_date_flight_predefined("next three months", 18);
	}
	@Test
	public void tc06_when_munually() {
		MainPage mp = new MainPage(driver);
		mp.set_flight_munually();
	}
	@Test
	public void tc07_where() {
		MainPage mp = new MainPage(driver);
		String expected = mp.fill_where_location("Freeport");
		DetailedItemPage dip = new DetailedItemPage(driver);
		String actual = dip.getItemTitle();
		Assert.assertEquals(actual.toLowerCase(), expected);
		driver.navigate().back();
	}
}
