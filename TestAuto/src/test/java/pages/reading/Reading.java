package pages.reading;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import Util.UtilTools;
import testauto.SharedDriver;

public class Reading extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(Reading.class.getName());
	private static final String READER_FORM_VALIDATED_TITLE_XPATH = ".//*[@id='tab-reading']/table[2]/tbody/tr[1]/td";

	private static final String READING_TREE_FLAG_VISIT_READ_STYLE = "background: url(\"js/themes/icon/ok16.png\") center center no-repeat rgb(131, 159, 196);";
	private static final String FMT_READING_TREE_FLAG_VISIT_XPATH = "//li[@title='%s']/ul/li/a[contains(text(),'%s')]/following::ins[1]";

	// Reader Tab
	public static String READER_FORM_LINK_XPATH = ".//*[@id='tabs']/ul/li[1]/a";
	public static String READER_FORM_LINK_LABEL = "Reading";
	// Reader form title
	public static String READER_FORM_TITLE_XPATH = ".//*[@id='tab-reading']/h2";
	public static String READER_FORM_TITLE_LABEL = "Reading";
	// validating Reader form button
	public static String READER_FORM_VALIDATE_BTN_XPATH = ".//*[@id='tab-reading']/form/table/tbody/tr[2]/td/div/a/span/span";
	public static String READER_FORM_VALIDATE_BTN_LABEL = "Validate";

	public static void CheckForm() throws Throwable {
		LOGGER.info("check Read Form");
		UtilTools.checkLabelIsDisplayed(READER_FORM_LINK_XPATH, READER_FORM_LINK_LABEL);
		UtilTools.checkLabelIsDisplayed(READER_FORM_TITLE_XPATH, READER_FORM_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(READER_FORM_VALIDATE_BTN_XPATH, READER_FORM_VALIDATE_BTN_LABEL);
	}

	public static void ValidateTheForm() throws Throwable {
		UtilTools.clickOnButton(READER_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void FormIsValidated(String patient, String visit) throws Throwable {
		LOGGER.info("Reading form IS validated");
		UtilTools.checkLabelIsDisplayed(READER_FORM_VALIDATED_TITLE_XPATH, "Reading, by ");
		checkFlagVisitReadDone(patient, visit);
		// TODO
		// Check the reading title when the form is validated
		// Check the name and first name of the reader and check the
		// time
	}

	public static void visitIsNoMoreDisplayedIntheTree(String Patient, String Visit) {

		String xpath = ".//*[@title='" + Patient + "']/ul[1]/li[1]/a[1]";

		if (UtilTools.checkLabelContentsNotDisplayed(xpath, Visit)) {
			LOGGER.info(String.format("Patient: '%s' and visit '%s' are still present in the tree", Patient, Visit));
			Assert.fail();
		} else {
			LOGGER.info(String.format("Patient: '%s' and visit '%s' ARE NOT present in the tree", Patient, Visit));
		}
	}

	public static boolean checkFlagVisitReadDone(String patient, String visit) {

		String xpath = String.format(FMT_READING_TREE_FLAG_VISIT_XPATH, patient, visit);
		if (UtilTools.checkImageIsDisplayed(xpath, READING_TREE_FLAG_VISIT_READ_STYLE))
			return true;

		LOGGER.severe("The visit flag, for Read done, is not the good one");

		Assert.fail();
		return false;
	}

}
