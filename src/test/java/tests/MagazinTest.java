package tests;

import org.testng.annotations.Test;

import objects.ArticlePage;
import objects.MagazinPage;
import objects.MainPage;
import objects.NavPage;

public class MagazinTest extends BaseTest {
	@Test
	public void tc01_goTomagazin() {
		MainPage mp = new MainPage(driver);
		mp.got_It();
		NavPage np = new NavPage(driver);
		np.openMagazinPage();
		MagazinPage magazin = new MagazinPage(driver);
		magazin.check_titlePage("Articles");
		magazin.clickOn_artical("10 Best Theme Parks in Europe for 2021 (100% safe for Kids)"); //In that title Sacred Cheese, Enchantad Fries: Amsterdam for Foodies
																																				//There is a bug in the automation.(in easual ux it work

	}
	@Test
	public void tc02_check_social_links() {
		ArticlePage ap = new ArticlePage(driver);
		ap.scroll_to_buttom();
		ap.check_social_links();
	}
	@Test
	public void tc03_check_services_links(){
		ArticlePage ap = new ArticlePage(driver);
		ap.check_services_links();
	}
}
