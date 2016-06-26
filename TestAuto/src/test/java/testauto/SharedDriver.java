package testauto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;

public class SharedDriver {

	public static RemoteWebDriver driver;
	public static UtilTools util;

	private final static Logger LOGGER = Logger.getLogger(SharedDriver.class.getName());

	
	protected static RemoteWebDriver getDriver() {

		if (null == driver) {

			// To launch IC connector Firefox ask to autorize the "launch application" : ims
			// when a new instance of RemoteWebBrowser is created, a new profile is created
			// and all the configuration is loose! (you will need to autorize the ims application each time) to avoid that
			// you must create a profile on the hub : firefox -p (for example : DefaultUser) and use it to save his configuration
			// and configure the hub server to use this new profile
			// java  -jar selenium-server-standalone-2.53.0.jar -Dwebdriver.firefox.profile=DefaultUser ....
					
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			
			if (cap.getBrowserName().equals("firefox")) {
				cap.setCapability("jenkins.label", "Win8 & Firefox");
			}
			
			if (cap.getBrowserName().equals("internet explorer")) {
				cap.setCapability("jenkins.label", "Win8 & Internet Explorer");
			}
				
			if (cap.getBrowserName().equals("chrome")) {
				cap.setBrowserName(cap.getBrowserName());
				cap.setCapability("jenkins.label", "Win8 & Chrome");
				ChromeOptions options = new ChromeOptions();
				
				// To launch IC connector chrome ask in the window : "external protocol handler" to autorize the request : ims
				// when a new instance of RemoteWebBrowser is created, a new profile is created
				// and all the configuration is loose! (you will need to autorize the ims application each time) to avoid that
				// you must create a profile on the hub manually by open chrome, and autorize the request ims
				// and set the good hub path profil in the user-data-dir
							
				options.addArguments("user-data-dir=C:/Users/pdu/AppData/Local/Google/Chrome/User Data");
				cap.setCapability(ChromeOptions.CAPABILITY, options);
			}

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
