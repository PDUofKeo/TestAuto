package pages.supervision.details.results.investigation;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import pages.supervision.details.results.qualitycontrol.QualityControl;
import testauto.SharedDriver;

public class Investigation  extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(Investigation.class.getName());

	public static final String RESULTS_INV_UNLOCK_BTN_XPATH = ".//*[@id='desactivateUpload']/span/span";
	public static final String RESULTS_INV_DELETE_BTN_XPATH = ".//*[@id='deleteUpload']/span/span";

	public static final String RESULTS_INV_UNLOCK_BTN_LABEL = "Unlock upload form";
	public static final String RESULTS_INV_DELETE_BTN_LABEL = "Delete upload form";


	private static final String RESULTS_INV_STATUS_VALUE_NOT_YET_DONE_LABEL = "Investigation has not been done yet";
	private static final String RESULTS_INV_STATUS_VALUE_NOT_YET_DONE_XPATH = ".//*[@id='tab-investigation'][contains(text(),'" + RESULTS_INV_STATUS_VALUE_NOT_YET_DONE_LABEL + "')]";
	
	
	
	public static boolean PageDetails(String patient, String visit) {
		boolean result = false;
		// Patient > Visit
		String patVisit = patient + " " + visit;
		result = UtilTools.checkLabelIsDisplayed(".//*[@id='bread_crumbs']", patVisit);

		// "Unlock" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_INV_UNLOCK_BTN_XPATH, RESULTS_INV_UNLOCK_BTN_LABEL);

		// "Delete" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_INV_DELETE_BTN_XPATH, RESULTS_INV_DELETE_BTN_LABEL);

		// TODO
		// check Upload control title : Upload, by <firstname>
		// <Surname> at DD/MM/YYYY hh:mm

		return result;
	}
	
	public static boolean CheckQCNotYetDone() {
		LOGGER.info("CheckQCNotYetDone ");
		if (UtilTools.checkLabelIsDisplayed(RESULTS_INV_STATUS_VALUE_NOT_YET_DONE_XPATH,
				RESULTS_INV_STATUS_VALUE_NOT_YET_DONE_LABEL))
			return true;
		return false;
	}

}
