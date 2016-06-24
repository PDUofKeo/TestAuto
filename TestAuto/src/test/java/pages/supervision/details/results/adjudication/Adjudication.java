package pages.supervision.details.results.adjudication;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import testauto.SharedDriver;

public class Adjudication  extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(Adjudication.class.getName());

	private static final String RESULTS_ADJUDICATION_UNLOCK_BTN_XPATH = ".//*[@id='desactivateResult']/span/span";
	private static final String RESULTS_ADJUDICATION_UNLOCK_BTN_LABEL = "Unlock adjudication";

	public static final String RESULTS_ADJUDICATION_DELETE_BTN_XPATH = ".//*[@id='deleteResult']/span/span";
	private static final String RESULTS_ADJUDICATION_DELETE_BTN_LABEL = "Delete adjudication";

	private static final String RESULTS_ADJUDICATION_STATUS_VALUE_NOT_YET_DONE_XPATH = ".//*[@id='tab-adjudication']";
	private static final String RESULTS_ADJUDICATION_STATUS_VALUE_NOT_YET_DONE_LABEL = "Adjudication has not been done yet";
	
	
	public static boolean PageDetails(String patient, String visit) {
		boolean result = false;
		// Patient > Visit
		String patVisit = patient + " " + visit;
		result = UtilTools.checkLabelIsDisplayed(".//*[@id='bread_crumbs']", patVisit);

		// "Unlock" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_ADJUDICATION_UNLOCK_BTN_XPATH, RESULTS_ADJUDICATION_UNLOCK_BTN_LABEL);

		// "Delete" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_ADJUDICATION_DELETE_BTN_XPATH, RESULTS_ADJUDICATION_DELETE_BTN_LABEL);

		// TODO
		// check Upload control title : Upload, by <firstname>
		// <Surname> at DD/MM/YYYY hh:mm

		return result;
		
	}
	
	public static boolean CheckNotYetDone() {
		LOGGER.info("CheckQCNotYetDone ");
		if (UtilTools.checkLabelIsDisplayed(RESULTS_ADJUDICATION_STATUS_VALUE_NOT_YET_DONE_XPATH,
				RESULTS_ADJUDICATION_STATUS_VALUE_NOT_YET_DONE_LABEL))
			return true;
		return false;
	}
}
