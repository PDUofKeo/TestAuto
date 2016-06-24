package pages.common;

import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import Util.UtilTools;
import cucumber.api.java.en.Given;
import testauto.SharedDriver;
import testauto.StepDefinitions;

public class UserPage extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	private final static Logger LOGGER = Logger.getLogger(UserPage.class.getName());
	

	public static String CONTROLLER_TAB_XPATH = ".//*[@id='tab_2']";
	public static String CONTROLLER_TAB_LABEL = "Controller";
	private static final String READER_TAB_XPATH = ".//*[@id='tab_3']/span/span";
	private static final String READER_TAB_LABEL = "Reader";
	private static final String ADJUDICATOR_TAB_XPATH = ".//*[@id='tab_4']/span/span";
	private static final String ADJUDICATOR_TAB_LABEL = "Adjudicator";
	
	public static String SUPERVISOR_TAB_XPATH = ".//*[@id='tab_5']";
	public static String SUPERVISOR_TAB_LABEL = "Supervisor";
	
	public static String LOGOUT_BTN_XPATH = ".//*[@id='tab_logout']/span/span";
	public static String LOGOUT_BTN_LABEL = "Logout";

	public static String SIGN_TITLE_XPATH = ".//*[@id='principal_zone']/fieldset/legend";
	public static String SIGN_TITLE_LABEL = "Please sign in";

	public static void CheckUserIsDisconnected() throws Throwable {

		if (UtilTools.checkLabelIsDisplayed(SIGN_TITLE_XPATH, SIGN_TITLE_LABEL)) {
			LOGGER.info("User is DISCONNECTED");

		} else {
			LOGGER.severe("User is CONNECTED");
			Assert.fail();
		}

		/*if (null != driver)
			driver.quit();*/
	}

	public static void CheckUserIsConnected() throws Throwable {

		if (UtilTools.checkLabelIsDisplayed(LOGOUT_BTN_XPATH, LOGOUT_BTN_LABEL))
			LOGGER.info("User is Connected");
		else {
			LOGGER.severe("User is NOT Connected");
			Assert.fail();
		}

	}

	public static void CheckControllerTabIsDisplayed() throws Throwable {
		if (UtilTools.checkLabelIsDisplayed(CONTROLLER_TAB_XPATH, CONTROLLER_TAB_LABEL))
			LOGGER.info("Controller tab is displayed");
		else {
			LOGGER.severe("Controller tab is NOT displayed");
			Assert.fail();
		}
	}
	
	public static void CheckSupervisorTabIsDisplayed() throws Throwable {
		if (UtilTools.checkLabelIsDisplayed(SUPERVISOR_TAB_XPATH, SUPERVISOR_TAB_LABEL))
			LOGGER.info("Supervisor tab is displayed");
		else {
			LOGGER.severe("Supervisor tab is NOT displayed");
			Assert.fail();
		}
	}
	
	public static void CheckReaderTabIsDisplayed() throws Throwable {
		if (UtilTools.checkLabelIsDisplayed(READER_TAB_XPATH, READER_TAB_LABEL))
			LOGGER.info("Reader tab is displayed");
		else {
			LOGGER.severe("Reader tab is NOT displayed");
			Assert.fail();
		}
	}
	
	public static void CheckAdjudicatorTabIsDisplayed() throws Throwable {
		if (UtilTools.checkLabelIsDisplayed(ADJUDICATOR_TAB_XPATH, ADJUDICATOR_TAB_LABEL))
			LOGGER.info("Adjudicator tab is displayed");
		else {
			LOGGER.severe("Adjudicator tab is NOT displayed");
			Assert.fail();
		}
	}
		
		
	public static void ClickOntheControllerTab(){
		LOGGER.info("User clicks on the controller tab");
		UtilTools.clickOnButton(CONTROLLER_TAB_XPATH);
	}
	
	public static void ClickOntheSupervisorTab(){
		LOGGER.info("User clicks on the supervisor tab");
		UtilTools.clickOnButton(SUPERVISOR_TAB_XPATH);
	}
	
	public static void ClickOntheReaderTab(){
		LOGGER.info("User clicks on the Reader tab");
		UtilTools.clickOnButton(READER_TAB_XPATH);
	
	}
	
	public static void ClickOntheAdjudicatorTab(){
		LOGGER.info("User clicks on the Adjudicator tab");
		UtilTools.clickOnButton(ADJUDICATOR_TAB_XPATH);
	
	}

}
