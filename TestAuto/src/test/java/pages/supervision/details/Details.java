package pages.supervision.details;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import testauto.SharedDriver;

public class Details extends SharedDriver {

	public RemoteWebDriver driver= getDriver();
	private final static Logger LOGGER = Logger.getLogger(Details.class.getName());

	public static String RESULTS_TAB_XPATH = ".//*[@id='central_page']/ul/li[5]/a";

	public static boolean DetailPageDetails(String patient, String visit) {

		LOGGER.info("Verify Details page");
		
		// Patient > Visit
		String patVisit = patient + " " + visit;
		return UtilTools.checkLabelIsDisplayed(".//*[@id='bread_crumbs']", patVisit);
	}

	public static void UserClicksOnResultsBtn() {
		if (UtilTools.clickOnButton(RESULTS_TAB_XPATH))
			LOGGER.info(String.format("click on results btn. Xpath:'%s' ", RESULTS_TAB_XPATH));
		else
			LOGGER.severe(String.format("Cannot Click on the results btn. Xpath:'%s' ", RESULTS_TAB_XPATH));

	}

}
