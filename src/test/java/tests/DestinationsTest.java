package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import objects.DestanitionsPage;
import objects.DetaildDestinationPage;
import objects.NavPage;

public class DestinationsTest extends BaseTest{
	@Test
	public void tc01_destinations() {
		NavPage np = new NavPage(driver);
		np.openDestinationPage();
		Assert.assertTrue(np.isValidDestinationPage("Destinations - Shichor"));
	}

	@Test
	public void tc02_popular_destinations() {
		DestanitionsPage dp = new DestanitionsPage(driver);
//		dp.waiting(4000);
		dp.popularItemByTitle("Amsterdam, Netherlands");
//		dp.populaItemByIndex(5);
		DetaildDestinationPage ddp = new DetaildDestinationPage(driver);
		Assert.assertTrue(ddp.isValiDetailedDestinationPage("Amsterdam, Netherlands - Shichor"));
		driver.navigate().back();
		NavPage np = new NavPage(driver);
		Assert.assertTrue(np.isValidDestinationPage("Destinations - Shichor"));
	}

	@Test
	public void tc03_popular_destinations() {
		DestanitionsPage dp = new DestanitionsPage(driver);
		String titleItem = dp.popularItemByIndex(2);
		DetaildDestinationPage ddp = new DetaildDestinationPage(driver);
		Assert.assertTrue(ddp.isValiDetailedDestinationPage(titleItem));
	}
}
