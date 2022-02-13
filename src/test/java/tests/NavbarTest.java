package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import objects.AboutPage;
import objects.MagazinPage;
import objects.MainPage;
import objects.NavPage;

public class NavbarTest extends BaseTest {

	
	 @Test 
	 public void tc01_sign_up_boundaries() {
		 MainPage mp = new
		 MainPage(driver);
		 mp.check_boundaries("1234542","1234542","yakov.sach.com","12312eee1");
	 }

	@Test
	public void tc02_sign_up() {
		AboutPage ap = new AboutPage(driver);
		ap.sign_up("yakov", "sachuk", "yakov.sachuk@gmail.com", "19Shichor48");
	}
	
	@Test
	public void tc03_login() {
		MainPage mp = new MainPage(driver);
		mp.login();
	}
	@Test
	public void tc04_about() {
		MainPage mp = new MainPage(driver);
		mp.openAboutPage();
	}
	@Test
	public void tc05_link_home() {
		AboutPage ap = new AboutPage(driver);
		ap.click_on_homeLogo();
	}
}
