package pages.supervision.details.results.qualitycontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.UtilTools;
import pages.supervision.details.results.Entity;
import testauto.SharedDriver;

public class QualityControl extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(QualityControl.class.getName());

	
	
	
	
	
	// QC form in the supervisor interface
	public static final String RESULTS_QC_DELETE_BTN_XPATH = ".//*[@id='deleteQC']/span/span";
	public static final String RESULTS_QC_DELETE_BTN_LABEL = "Delete QC";
	public static final String RESULTS_QC_UNLOCK_BTN_XPATH = ".//*[@id='desactivateQC']/span/span";
	public static final String RESULTS_QC_UNLOCK_BTN_LABEL = "Unlock QC";
	public static final String RESULTS_QC_STATUS_TITLE_XPATH = ".//*[@id='tab-qc']/table[2]/tbody/tr[2]/th";
	public static final String RESULTS_QC_STATUS_TITLE_LABEL = "Status";
	public static final String RESULTS_QC_STATUS_VALUE_XPATH = ".//*[@id='tab-qc']/table[2]/tbody/tr[2]/td";

	// QC status
	public static final String RESULTS_QC_STATUS_VALUE_ACCEPTED_LABEL = "Quality control accepted";
	public static final String RESULTS_QC_STATUS_VALUE_REFUSED_LABEL = "Quality control refused";
	public static final String RESULTS_QC_STATUS_VALUE_ASK_CORRECTIVE_ACTION_LABEL = "Quality control waiting corrective action";

	public static final String RESULTS_QC_STATUS_VALUE_NOT_YET_DONE_XPATH = ".//*[@id='tab-qc']";
	public static final String RESULTS_QC_STATUS_VALUE_NOT_YET_DONE_LABEL = "Quality control has not been done yet";

	public static boolean AcceptedQCPageDetails(String patient, String visit) {

		boolean result = false;

		// QC status value
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_QC_STATUS_VALUE_XPATH,
				RESULTS_QC_STATUS_VALUE_ACCEPTED_LABEL);

		if (false == result) {
			LOGGER.severe(String.format("The QC status for the Patient '%s' and the visit '%s' is not '%s'", patient,
					visit, RESULTS_QC_STATUS_VALUE_ACCEPTED_LABEL));

		} else {
			LOGGER.info(String.format("The QC status for the Patient '%s' and the visit '%s' is '%s'", patient, visit,
					RESULTS_QC_STATUS_VALUE_ACCEPTED_LABEL));

		}

		result = result & QCPageDetails(patient, visit);
		return result;
	}

	public static boolean RefusedQCPageDetails(String patient, String visit) {

		boolean result = false;

		// QC status value
		result = result
				& UtilTools.checkLabelIsDisplayed(RESULTS_QC_STATUS_VALUE_XPATH, RESULTS_QC_STATUS_VALUE_REFUSED_LABEL);

		if (false == result) {
			LOGGER.severe(String.format("The QC status for the Patient '%s' and the visit '%s' is not '%s'", patient,
					visit, RESULTS_QC_STATUS_VALUE_REFUSED_LABEL));

		} else {
			LOGGER.info(String.format("The QC status for the Patient '%s' and the visit '%s' is '%s'", patient, visit,
					RESULTS_QC_STATUS_VALUE_REFUSED_LABEL));

		}

		result = result & QCPageDetails(patient, visit);
		return result;
	}

	public static boolean AskedCorrectiveActionQCPageDetails(String patient, String visit) {

		boolean result = false;

		// QC status value
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_QC_STATUS_VALUE_XPATH,
				RESULTS_QC_STATUS_VALUE_ASK_CORRECTIVE_ACTION_LABEL);
		if (false == result) {
			LOGGER.severe(String.format("The QC status for the Patient '%s' and the visit '%s' is not '%s'", patient,
					visit, RESULTS_QC_STATUS_VALUE_ASK_CORRECTIVE_ACTION_LABEL));

		} else {
			LOGGER.info(String.format("The QC status for the Patient '%s' and the visit '%s' is '%s'", patient, visit,
					RESULTS_QC_STATUS_VALUE_ASK_CORRECTIVE_ACTION_LABEL));

		}

		result = result & QCPageDetails(patient, visit);
		return result;
	}

	private static boolean QCPageDetails(String patient, String visit) {
		boolean result = false;
		// Patient > Visit
		String patVisit = patient + " " + visit;
		result = UtilTools.checkLabelIsDisplayed(".//*[@id='bread_crumbs']", patVisit);

		// status title
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_QC_STATUS_TITLE_XPATH, RESULTS_QC_STATUS_TITLE_LABEL);
		// "Unlock" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_QC_UNLOCK_BTN_XPATH, RESULTS_QC_UNLOCK_BTN_LABEL);

		// "Delete QC" button
		result = result & UtilTools.checkLabelIsDisplayed(RESULTS_QC_DELETE_BTN_XPATH, RESULTS_QC_DELETE_BTN_LABEL);

		// TODO
		// check Quality control title : Quality Control, by <firstname>
		// <Surname> at DD/MM/YYYY hh:mm

		return result;
	}

	/**
	 * @param driver
	 */
	public static void QCDeletedReason(String comment) {

		long timeout = 2;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Entity.REASON_DELETED_ENTITY_TITLE_XPATH)));

		} catch (Exception e) {
			LOGGER.severe(String.format("timeout (%d s) reached for xpath= %s .", timeout,
					Entity.REASON_DELETED_ENTITY_TITLE_XPATH));
			Assert.fail();
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		String commentText = comment + " " + dateFormat.format(date);
		LOGGER.info("Set Deleted QC reason comment: " + "'" + commentText + "'");

		UtilTools.setInputFieldTextValue(Entity.REASON_DELETED_ENTITY_COMMENT_XPATH, commentText);

	}

	/**
	 * @param driver
	 */
	public static void QCResultsIsDeleted(RemoteWebDriver driver) {

		long timeout = 2;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Entity.REASON_DELETED_ENTITY_TITLE_LABEL)));

		} catch (Exception e) {
			String.format("timeout (%d s) reached for xpat=%s .", timeout, Entity.REASON_DELETED_ENTITY_TITLE_LABEL);
			Assert.fail();
		}

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		String.format("xpath: '%s' is visible", Entity.REASON_DELETED_ENTITY_TITLE_LABEL);
		UtilTools.setInputFieldTextValue(Entity.REASON_DELETED_ENTITY_COMMENT_XPATH,
				"Delete QC at " + dateFormat.format(date));

	}

	public static boolean CheckQCNotYetDone() {
		LOGGER.info("CheckQCNotYetDone ");
		if (UtilTools.checkLabelIsDisplayed(RESULTS_QC_STATUS_VALUE_NOT_YET_DONE_XPATH,
				RESULTS_QC_STATUS_VALUE_NOT_YET_DONE_LABEL))
			return true;

		return false;
	}

}
