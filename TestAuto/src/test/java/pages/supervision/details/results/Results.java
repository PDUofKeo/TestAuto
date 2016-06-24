package pages.supervision.details.results;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import pages.supervision.details.Details;
import testauto.SharedDriver;

public class Results extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();
	private final static Logger LOGGER = Logger.getLogger(Results.class.getName());
	
	
	// QC tab in Results page from supervision menu
	public static String RESULTS_QC_FORM_LINK_XPATH = ".//*[@href='#tab-qc']";
	public static String RESULTS_QC_FORM_LINK_LABEL = "Quality Control";
	public static String RESULTS_INVESTIGATION_FORM_LINK_XPATH = ".//*[@href='#tab-investigation']";
	public static String RESULTS_INVESTIGATION_FORM_LINK_LABEL = "Investigation";
	private static final String RESULTS_READING_FORM_LINK_XPATH = ".//*[@href='#tab-reading']";
	private static final String RESULTS_READING_FORM_LINK_LABEL = "Reading";
	private static final String RESULTS_ADJUDICATION_FORM_LINK_XPATH = ".//*[@href='#tab-adjudication']";

	
	public static boolean ResultsPageDetails(String patient, String visit) {

		LOGGER.info("Verify Results page conformity");

		boolean result = false;
		// Patient > Visit
		// Remove cause it is generating statelement error
		// result = Details.DetailPageDetails(patient, visit);

		// Investigation tab title
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_INVESTIGATION_FORM_LINK_XPATH,
				RESULTS_INVESTIGATION_FORM_LINK_LABEL);
		// Quality control tab title
		result = result
				& UtilTools.checkLabelIsDisplayed(RESULTS_QC_FORM_LINK_XPATH, RESULTS_QC_FORM_LINK_LABEL);

		return result;

	}

	public static void SupervisorClicksOnQualityControlTab() {
		LOGGER.info("User click on Quality control tab from Results page");
		UtilTools.clickOnButton(RESULTS_QC_FORM_LINK_XPATH);
	}

	public static void SupervisorClicksOnInvestigationTab() {
		LOGGER.info("User click on Investigation tab from Results page");
		UtilTools.clickOnButton(RESULTS_INVESTIGATION_FORM_LINK_XPATH);
	}
	
	public static void SupervisorClicksOnReadingTab() {
		LOGGER.info("User click on Reading tab from Results page");
		UtilTools.clickOnButton(RESULTS_READING_FORM_LINK_XPATH);
	}
	
	public static void SupervisorClicksOnAdjudicationTab() {
		LOGGER.info("User click on Adjudication tab from Results page");
		UtilTools.clickOnButton(RESULTS_ADJUDICATION_FORM_LINK_XPATH);
	}

}