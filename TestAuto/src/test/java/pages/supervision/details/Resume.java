package pages.supervision.details;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import pages.supervision.details.results.Results;
import testauto.SharedDriver;

public class Resume extends SharedDriver {

	private final static Logger LOGGER = Logger.getLogger(Results.class.getName());
	public static RemoteWebDriver driver = getDriver();

	public static boolean ResumePageDetails(String patient, String visit) {

		boolean result = false;

		LOGGER.info("Verify Resume page");

		// Patient > Visit
		result = Details.DetailPageDetails(patient, visit);

		// Patient code title
		result = result & UtilTools.checkLabelIsDisplayed(".//*[@id='central_page']/div[2]/table/tbody/tr[2]/th",
				"Patient code");
		// Visit code title
		result = result
				& UtilTools.checkLabelIsDisplayed(".//*[@id='central_page']/div[2]/table/tbody/tr[3]/th", "Visit code");
		// Resume title
		result = result
				& UtilTools.checkLabelIsDisplayed(".//*[@id='central_page']/div[2]/table/tbody/tr[1]/td", "Resume");

		result = result
				& UtilTools.checkLabelIsDisplayed(".//*[@id='central_page']/div[2]/table/tbody/tr[2]/td", patient);
		result = result
				& UtilTools.checkLabelIsDisplayed(".//*[@id='central_page']/div[2]/table/tbody/tr[3]/td", visit);

		return result;
	}

}
