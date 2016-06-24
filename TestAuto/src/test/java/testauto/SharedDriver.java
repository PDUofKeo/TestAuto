package testauto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;

public class SharedDriver {

	public static RemoteWebDriver driver;
	public static UtilTools util;

	private final static Logger LOGGER = Logger.getLogger(SharedDriver.class.getName());

	protected UtilTools getTools(){
		if(null == util){
			util = new UtilTools();
		}
		return util;
	}
	
	
	protected static RemoteWebDriver getDriver()  {

		if (null == driver) {
			
			//ProfilesIni profile = new ProfilesIni();
			 
			//FirefoxProfile myprofile = profile.getProfile("TEST");
			//FirefoxProfile myprofile = new FirefoxProfile(new File("C:/Users/pdu/AppData/Local/Mozilla/Firefox/Profiles"));
			// FirefoxProfile myprofile = new ProfilesIni().getProfile("Default");
			// cap.setCapability(FirefoxDriver.PROFILE, myprofile);
			//cap.setCapability(FirefoxDriver.PROFILE, myprofile);
			
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName(cap.getBrowserName());
			cap.setCapability("jenkins.label", "Win8 & Internet explorer");
			
		
			try {
				driver = new RemoteWebDriver(new URL("http://10.44.5.20:5555/wd/hub"), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			String webURL = "https://generic-test.imagys.com";

			driver.navigate().to(webURL);
			LOGGER.info("siteweb: " + webURL);

		}

		return driver;

	}
	
	

}
