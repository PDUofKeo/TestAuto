package pages.supervision.details.results.reading;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import pages.supervision.details.results.qualitycontrol.QualityControl;
import testauto.SharedDriver;

public class Reading extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(QualityControl.class.getName());

	public static String RESULTS_READING_SECOND_DELETE_BTN_XPATH = ".//*[@id='tab-reading']/div[2]/table[3]/tbody/tr[1]/td[1]/div/span/span";
	public static String RESULTS_READING_FIRST_DELETE_BTN_XPATH = ".//*[@id='tab-reading']/div[1]/table[3]/tbody/tr[1]/td[1]/div/span/span";
	public static String RESULTS_READING_DELETE_BTN_LABEL = "Delete reading";

	public static String RESULTS_READING_SECOND_UNLOCK_BTN_XPATH = ".//*[@id='tab-reading']/div[2]/table[3]/tbody/tr[1]/td[2]/div/span/span";
	public static String RESULTS_READING_FIRST_UNLOCK_BTN_XPATH = ".//*[@id='tab-reading']/div[1]/table[3]/tbody/tr[1]/td[2]/div/span/span";
	public static String RESULTS_READING_UNLOCK_BTN_LABEL = "Unlock reading";

	public static boolean PageDetailsWhen2Readings(String patient, String visit) {
		boolean result = false;
		// Patient > Visit
		String patVisit = patient + " " + visit;
		result = UtilTools.checkLabelIsDisplayed(".//*[@id='bread_crumbs']", patVisit);

		// "Unlock" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_READING_SECOND_DELETE_BTN_XPATH,
				RESULTS_READING_DELETE_BTN_LABEL);

		// "Delete" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_READING_SECOND_UNLOCK_BTN_XPATH,
				RESULTS_READING_UNLOCK_BTN_LABEL);

		// TODO
		// check reading control title : Reading, by <firstname>
		// <Surname> at DD/MM/YYYY hh:mm

		return result;

	}

	/**
	 * Checking the second reading form is deleted We check there are no more
	 * buttons
	 * 
	 * @return
	 */
	public static boolean checkSecondReadingFormIsDeleted() {
		boolean lResult = false;

		lResult = UtilTools.checkLabelContentsNotDisplayed(RESULTS_READING_SECOND_DELETE_BTN_XPATH,
				RESULTS_READING_DELETE_BTN_LABEL);
		lResult = lResult & UtilTools.checkLabelContentsNotDisplayed(RESULTS_READING_SECOND_UNLOCK_BTN_XPATH,
				RESULTS_READING_UNLOCK_BTN_LABEL);

		return lResult;
	}

}
