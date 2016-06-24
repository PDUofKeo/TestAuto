package pages.supervision;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Util.UtilTools;
import testauto.SharedDriver;

public class Supervision extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	public static String SUPERVISOR_SUPERVISION_LINK_XPATH = ".//*[@id='tab_5']/span/span";
	public static String SUPERVISOR_SUPERVISION_LINK_LABEL = "Supervisor";
	// Visits/Series
	public static String SUPERVISOR_SUPERVISION_VISIT_SERIES_XPATH = ".//*[@id='central_page']/ul/li[1]/a";
	public static String SUPERVISOR_SUPERVISION_VISIT_SERIES_LABEL = "Visits/Series";
	// Filter button to launch search data
	public static String SUPERVISOR_SUPERVISION_FILTER_BTN_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[1]/td[5]/a";

	// Patient title of the first column
	public static String SUPERVISOR_PATIENT_TITLE_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[2]/td[1]";
	public static String SUPERVISOR_PATIENT_TITLE_LABEL = "Patient";
	// Visit title of the secund column
	public static String SUPERVISOR_VISIT_TITLE_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[2]/td[2]";
	public static String SUPERVISOR_VISIT_TITLE_LABEL = "Visit";
	// Upload title of the third column
	public static String SUPERVISOR_UPLOAD_TITLE_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[2]/td[3]";
	public static String SUPERVISOR_UPLOAD_TITLE_LABEL = "Upload";
	// Status title of the fourth column
	public static String SUPERVISOR_STATUS_TITLE_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[2]/td[4]";
	public static String SUPERVISOR_STATUS_TITLE_LABEL = "Status";
	// Status title of the fifth column
	public static String SUPERVISOR_DETAILS_TITLE_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[2]/td[5]";
	public static String SUPERVISOR_DETAILS_TITLE_LABEL = "Details";

	// Details button
	public static String SUPERVISOR_SUPERVISION_DETAILS_BTN_XPATH = ".//*[@id='supervision']/table[2]/tbody/tr[3]/td[5]/a";

	private final static Logger LOGGER = Logger.getLogger(Supervision.class.getName());

	public static void SupervisorTableDetail() throws Throwable {

		UtilTools.checkLabelIsDisplayed(SUPERVISOR_SUPERVISION_LINK_XPATH, SUPERVISOR_SUPERVISION_LINK_LABEL);
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_SUPERVISION_VISIT_SERIES_XPATH,
				SUPERVISOR_SUPERVISION_VISIT_SERIES_LABEL);
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_PATIENT_TITLE_XPATH, SUPERVISOR_PATIENT_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_VISIT_TITLE_XPATH, SUPERVISOR_VISIT_TITLE_LABEL);
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_UPLOAD_TITLE_XPATH, SUPERVISOR_UPLOAD_TITLE_LABEL);
		
		//LOGGER.info(String.format(" >>>>>>>>> STATUS '%s'", driver.findElement(By.xpath(SUPERVISOR_STATUS_TITLE_XPATH)).getText())); 
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_STATUS_TITLE_XPATH, SUPERVISOR_STATUS_TITLE_LABEL);
		//LOGGER.info(String.format(" >>>>>>>>> DETAILS '%s'", driver.findElement(By.xpath(SUPERVISOR_DETAILS_TITLE_XPATH)).getText())); 
		UtilTools.checkLabelIsDisplayed(SUPERVISOR_DETAILS_TITLE_XPATH, SUPERVISOR_DETAILS_TITLE_LABEL);
		
	}

	public static void SupervisorCliksOnTheFilterButton() throws Throwable {

		UtilTools.clickOnButton(SUPERVISOR_SUPERVISION_FILTER_BTN_XPATH);
	}

	public static void SupervisorCliksOnTheDetailsButton() throws Throwable {

		UtilTools.clickOnButton(SUPERVISOR_SUPERVISION_DETAILS_BTN_XPATH);
	}

}
