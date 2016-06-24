package pages.adjudication;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import Util.UtilTools;
import testauto.SharedDriver;

public abstract class Adjudication extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(Adjudication.class.getName());

	private static final String ADJUDICATOR_FORM_LINK_XPATH = ".//*[@id='tabs']/ul/li[4]/a";
	private static final String ADJUDICATOR_FORM_TITLE_XPATH = ".//*[@id='tab-adjudication']/h2";
	private static final String ADJUDICATOR_FORM_VALIDATE_BTN_XPATH = ".//*[@id='tab-adjudication']/form/table/tbody/tr[2]/td/div/a/span/span";

	private static final String ADJUDICATION_FORM_LINK_LABEL = "Adjudication";
	private static final String ADJUDICATION_FORM_TITLE_LABEL = "Adjudication";
	private static final String ADJUDICATION_FORM_VALIDATE_BTN_LABEL = "Validate";

	private static final String ADJUDICATION_FORM_VALIDATED_TITLE_XPATH = ".//*[@id='tab-adjudication']/table/tbody/tr[1]/td";

	private static final String FMT_ADJUDICATION_TREE_FLAG_VISIT_XPATH = "//li[@title='%s']/ul/li/a[contains(text(),'%s')]/following::ins[1]";
	private static final String ADJUDICATION_TREE_FLAG_VISIT_ADJUDICATED_STYLE = "//li[@title='%s']/ul/li/a[contains(text(),'%s')]/following::ins[1]";
	

	public static void checkForm() throws Throwable {
		LOGGER.info("checkINVForm");
		UtilTools.checkLabelIsDisplayed(ADJUDICATOR_FORM_LINK_XPATH, ADJUDICATION_FORM_LINK_LABEL);
		UtilTools.checkLabelIsDisplayed(ADJUDICATOR_FORM_TITLE_XPATH, ADJUDICATION_FORM_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(ADJUDICATOR_FORM_VALIDATE_BTN_XPATH, ADJUDICATION_FORM_VALIDATE_BTN_LABEL);
	}

	public static void ValidateTheForm() {
		UtilTools.clickOnButton(ADJUDICATOR_FORM_VALIDATE_BTN_XPATH);
		driver.switchTo().alert().accept();
	}

	public static void FormIsValidated() {
		LOGGER.info("Adjudication form IS validated");
		UtilTools.checkLabelIsDisplayed(ADJUDICATION_FORM_VALIDATED_TITLE_XPATH, "Adjudication, by");
		//TODO
		//check the flag
	}

	
	public static boolean checkFlagVisitReadDone(String patient, String visit) {

		String xpath = String.format(FMT_ADJUDICATION_TREE_FLAG_VISIT_XPATH, patient, visit);
		if (UtilTools.checkImageIsDisplayed(xpath, ADJUDICATION_TREE_FLAG_VISIT_ADJUDICATED_STYLE))
			return true;
		
		LOGGER.severe("The visit flag, for adjudication done, is not the good one");

		Assert.fail();
		return false;
	}

}
