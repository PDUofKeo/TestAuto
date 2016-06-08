package imagys.com.TestAuto;
import cucumber.api.java.en.Given;  
import cucumber.api.java.en.Then;  
import cucumber.api.java.en.When;

import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;


import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {

    public static RemoteWebDriver driver;

	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("QA");
				
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability("jenkins.label","redhat5 && amd64");
		driver = new RemoteWebDriver(new URL("http://10.44.5.20:5555/wd/hub"), cap);
				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://cati-test.imagys.com");
	}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password() throws Throwable {
		driver.findElement(By.name("login")).sendKeys("sysadmin");
		driver.findElement(By.name("password")).sendKeys("keosys");
		driver.findElement(By.xpath(".//*[@id='principal_zone']/fieldset/form/ol/li[3]/div/a/span/span")).click();
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {
		System.out.println("Login Successfully");
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	}

	@Then("^Message displayed LogOut Successfully$")
	public void message_displayed_Logout_Successfully() throws Throwable {
		System.out.println("LogOut Successfully");
	}

	@Then("^IC is displayed$")
	public void IC_is_displayed() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tab_logout'")));
			System.out.println("Imagys connector is displayed");
	}

	
	@Given("^User is connected$")
	public void User_is_connected() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='tab_logout']")));
		if ((driver.findElement(By.xpath(".//*[@id='tab_logout']/span/span")).getText()).contentEquals("Logout"))
			System.out.println("User is connected");
		else
			System.out.println("User is NOT connected");
	}
	
	
	public static String jvmBitVersion() {
		return System.getProperty("sun.arch.data.model");
	}

}