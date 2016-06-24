package pages.Investigation;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import Util.UtilTools;
import testauto.SharedDriver;

public class Investigation extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(Investigation.class.getName());

	// Investigation Tab
	public static String INV_FORM_LINK_XPATH = ".//*[@id='tabs']/ul/li[1]/a";
	public static String INV_FORM_LINK_LABEL = "Investigation";
	// Upload form title
	public static String INV_FORM_TITLE_XPATH = ".//*[@id='tab-investigation']/h2";
	public static String INV_FORM_TITLE_LABEL = "Upload form";
	// validating upload form button
	public static String INV_FORM_VALIDATE_BTN_XPATH = ".//*[@id='tab-investigation']/div/form/table/tbody/tr[2]/td/div/a/span/span";
	public static String INV_FORM_VALIDATE_BTN_LABEL = "Validate";

	// Title when form is validated
	public static String INV_FORM_VALIDATED_TITLE_XPATH = ".//*[@id='tab-investigation']/table[2]/tbody/tr[1]/td";

	public static void checkINVForm() throws Throwable {
		LOGGER.info("checkINVForm");
		UtilTools.checkLabelIsDisplayed(INV_FORM_LINK_XPATH, INV_FORM_LINK_LABEL);
		UtilTools.checkLabelIsDisplayed(INV_FORM_TITLE_XPATH, INV_FORM_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(INV_FORM_VALIDATE_BTN_XPATH, INV_FORM_VALIDATE_BTN_LABEL);
	}

	public static void ValidateTheInvForm() throws Throwable {
		UtilTools.clickOnButton(INV_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void InvestigationformIsValidated() throws Throwable {
		LOGGER.info("checkINVForm IS validated");
		UtilTools.checkLabelIsDisplayed(INV_FORM_VALIDATED_TITLE_XPATH, "Upload, by");
		// TODO
		// Check the upload title when the form is validated
		// Check the name and first name of the investigator and check the
		// time
	}

}
