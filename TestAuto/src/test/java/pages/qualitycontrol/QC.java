package pages.qualitycontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.UtilTools;
import testauto.SharedDriver;

public class QC extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(QC.class.getName());

	public static String QC_FORM_LINK_XPATH = ".//*[@id='tabs']/ul/li[1]/a";
	public static String QC_FORM_LINK_LABEL = "Quality Control";
	public static String QC_FORM_TITLE_XPATH = ".//*[@id='tab-qc']/h2";
	public static String QC_FORM_TITLE_LABEL = "Quality Control";
	public static String QC_FORM_CONCLUSION_TITLE_XPATH = ".//*[@id='formQC']/h3";
	public static String QC_FORM_CONCLUSION_TITLE_LABEL = "QC conclusion";
	public static String QC_FORM_VALIDATE_BTN_XPATH = ".//*[@id='formQC']/div/a/span/span";
	public static String QC_FORM_VALIDATE_BTN_LABEL = "Validate";

	public static String QC_FORM_ACCEPT_RADIO_XPATH = ".//*[@id='formQC']/input[1]";
	public static String QC_FORM_REFUSED_RADIO_XPATH = ".//*[@id='formQC']/input[2]";
	public static String QC_FORM_ASK_CORRECTIVE_ACTION_RADIO_XPATH = ".//*[@id='formQC']/input[3]";

	public static String QC_FORM_STATUS_LABEL = "Status";
	public static String QC_FORM_STATUS_XPATH = ".//*[@id='tab-qc']/table[2]/tbody/tr[2]/th";

	public static String QC_FORM_ACCEPTED_STATUS_LABEL = "Quality control accepted";
	public static String QC_FORM_REFUSED_STATUS_LABEL = "Quality control refused";
	public static String QC_FORM_ASK_CORRECTIVE_ACTION_LABEL = "Quality control waiting corrective action";
	public static String QC_FORM_STATUS_LABEL_XPATH = ".//*[@id='tab-qc']/table[2]/tbody/tr[2]/td";

	// Flags
	// OK
	public static String FMT_QC_TREE_FLAG_VISIT_XPATH = "//li[@title='%s']/ul/li/a[contains(text(),'%s')]/following::ins[1]";
	// Use the line above to get the style of the flag
	public static String QC_TREE_FLAG_VISIT_OK_STYLE = "background: rgb(131, 159, 196) url(\"js/themes/icon/ok16.png\") no-repeat scroll center center;";
	// Refused
	// Use the line above to get the style of the flag
	public static String QC_TREE_FLAG_VISIT_REFUSED_STYLE = "background: rgb(131, 159, 196) url(\"js/themes/icon/alert16.png\") no-repeat scroll center center;";
	public static String QC_TREE_FLAG_VISIT_CORRECTIVEACTION_STYLE = QC_TREE_FLAG_VISIT_REFUSED_STYLE;

	// Comment input field
	public static String QC_FORM_COMMENT_XPATH = ".//*[@id='formQC']/textarea";

	public static void checkQCForm() throws Throwable {
		LOGGER.info("checkQCForm");
		UtilTools.checkLabelIsDisplayed(QC_FORM_LINK_XPATH, QC_FORM_LINK_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_TITLE_XPATH, QC_FORM_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_CONCLUSION_TITLE_XPATH, QC_FORM_CONCLUSION_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_VALIDATE_BTN_XPATH, QC_FORM_VALIDATE_BTN_LABEL);
	}

	public static void acceptTheQCForm() throws Throwable {
		UtilTools.clickOnButton(QC_FORM_ACCEPT_RADIO_XPATH);
		UtilTools.clickOnButton(QC_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void refusedTheQCForm() throws Throwable {
		UtilTools.clickOnButton(QC_FORM_REFUSED_RADIO_XPATH);
		UtilTools.clickOnButton(QC_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void askACorrectiveActionForTheQCForm() throws Throwable {
		UtilTools.clickOnButton(QC_FORM_ASK_CORRECTIVE_ACTION_RADIO_XPATH);
		UtilTools.clickOnButton(QC_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void qcWasAccepted(String patient, String visit) throws Throwable {

		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_XPATH, QC_FORM_STATUS_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_LABEL_XPATH, QC_FORM_ACCEPTED_STATUS_LABEL);
		checkVisitFlagOK(patient, visit);
	}

	public static void qcWasRefused(String patient, String visit) throws Throwable {
		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_XPATH, QC_FORM_STATUS_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_LABEL_XPATH, QC_FORM_REFUSED_STATUS_LABEL);
		checkVisitFlagRefused(patient, visit);
	}

	public static boolean checkVisitFlagOK(String patient, String visit) {

		String xpath = String.format(FMT_QC_TREE_FLAG_VISIT_XPATH, patient, visit);
		if (UtilTools.checkImageIsDisplayed(xpath, QC_TREE_FLAG_VISIT_OK_STYLE))
			return true;

		LOGGER.severe("The visit flag, for accepted QC, is not the good one");

		Assert.fail();
		return false;
	}

	public static boolean checkVisitFlagRefused(String patient, String visit) {

		String xpath = String.format(FMT_QC_TREE_FLAG_VISIT_XPATH, patient, visit);
		if (UtilTools.checkImageIsDisplayed(xpath, QC_TREE_FLAG_VISIT_REFUSED_STYLE))
			return true;

		LOGGER.severe("The visit flag, for Refused QC, is not the good one");

		Assert.fail();
		return false;
	}

	public static boolean checkVisitFlagAskingCorrectiveAction(String patient, String visit) {

		String xpath = String.format(FMT_QC_TREE_FLAG_VISIT_XPATH, patient, visit);
		if (UtilTools.checkImageIsDisplayed(xpath, QC_TREE_FLAG_VISIT_CORRECTIVEACTION_STYLE))
			return true;

		LOGGER.severe("The visit flag, for Corrective action QC, is not the good one");

		Assert.fail();
		return false;
	}

	public static void qcWasAskedCorrectiveAction(String patient, String visit) throws Throwable {

		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_XPATH, QC_FORM_STATUS_LABEL);
		UtilTools.checkLabelIsDisplayed(QC_FORM_STATUS_LABEL_XPATH, QC_FORM_ASK_CORRECTIVE_ACTION_LABEL);
		checkVisitFlagAskingCorrectiveAction(patient, visit);
	}

	public static void setQCComment(String comment) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		long timeout = 2;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(QC_FORM_COMMENT_XPATH)));

		} catch (Exception e) {
			LOGGER.severe(String.format("timeout (%d s) reached for xpath= %s .", timeout, QC_FORM_COMMENT_XPATH));
			Assert.fail();
		}

		String commentText = comment + ": " + dateFormat.format(date);
		LOGGER.info("set QC comment: " + "'" + commentText + "'");
		UtilTools.setInputFieldTextValue(QC_FORM_COMMENT_XPATH, commentText);

	}

}
