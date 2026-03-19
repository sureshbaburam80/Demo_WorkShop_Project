package assertion_practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class assertion {
	public void ass()
	{
	WebDriver driver=new ChromeDriver();
	driver.get("http://www.google.com");
	String title =driver.getTitle();
	Assert.assertEquals(title, "Yahoomail");

}
}
