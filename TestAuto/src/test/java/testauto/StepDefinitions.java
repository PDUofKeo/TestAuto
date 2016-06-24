package testauto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import Util.UtilTools;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entity.Patient;
import entity.Site;
import entity.Visit;
import pages.Investigation.Investigation;
import pages.adjudication.Adjudication;
import pages.common.Credentials;
import pages.common.UserPage;
import pages.qualitycontrol.QC;
import pages.reading.Reading;
import pages.supervision.Supervision;
import pages.supervision.SupervisorSearchData;
import pages.supervision.details.Details;
import pages.supervision.details.Resume;
import pages.supervision.details.results.Entity;
import pages.supervision.details.results.Results;
import pages.supervision.details.results.ResultsData;
import pages.supervision.details.results.qualitycontrol.QualityControl;
import pages.supervision.details.results.qualitycontrol.QualityControlData;
import pages.supervision.details.resume.ResumeData;

public class StepDefinitions extends SharedDriver {

	private final static Logger LOGGER = Logger.getLogger(StepDefinitions.class.getName());

	public static RemoteWebDriver driver = getDriver();

	private static Scenario scenario;

	public static String LOGOUT_BTN_XPATH = ".//*[@id='tab_logout']";
	public static String LOGIN_BYNAME = "login";
	public static String PASSWORD_BYNAME = "password";
	public static String LOGIN_BTN_XPATH = ".//*[@id='principal_zone']/fieldset/form/ol/li[3]/div/a/span/span";

	@Given("^User is on Home Page$")
	public void User_is_on_Home_Page() throws Throwable {
		LOGGER.info("Open web driver");
	}

	@Before
	public void keepScenario(Scenario scenario) throws SQLException {
		StepDefinitions.scenario = scenario;

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("chicxulub");
		dataSource.setServerName("kam-test-db.keosys.local");
		Connection conn;
		conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ID FROM cati_imagys.USER");
		if (rs.next()) {
			LOGGER.info(String.format("Display %d,", rs.getInt("ID")));
		}

		rs.close();
		stmt.close();
		conn.close();

	}

	@When("^User enters UserName and Password$")
	public void user_enters_UserName_and_Password(List<Credentials> usercredentials) throws Throwable {
		for (Credentials credential : usercredentials) {
			driver.findElement(By.name(LOGIN_BYNAME)).sendKeys(credential.getUsername());
			driver.findElement(By.name(PASSWORD_BYNAME)).sendKeys(credential.getPassword());
			driver.findElement(By.xpath(LOGIN_BTN_XPATH)).click();
			LOGGER.info("User: " + credential.getUsername());
			LOGGER.info("Password: " + credential.getPassword());
			scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	@When("^User enters UserName: \"([^\"]*)\" and Password: \"([^\"]*)\"$")
	public void User_enters_UserName_and_Password(String Username, String Password) throws Throwable {
		
		long timeout = 15;
		
		try {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LOGIN_BYNAME)));
		} catch (Exception e) {
			LOGGER.severe(String.format("timeout: '%d' s reached for name:'%s'", timeout, LOGIN_BYNAME));
			Assert.fail();
		}
			
		driver.findElement(By.name(LOGIN_BYNAME)).sendKeys(Username);
		driver.findElement(By.name(PASSWORD_BYNAME)).sendKeys(Password);
		driver.findElement(By.xpath(LOGIN_BTN_XPATH)).click();
		scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		UtilTools.clickOnButton(LOGOUT_BTN_XPATH);
		LOGGER.info("User is logged out");
	}

	@Given("^User is connected$")
	public void User_is_connected() throws Throwable {

		// If IC is not working an alert message is displayed
		try {
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			LOGGER.info("Alert message may be not present");
		}
		
//		String mainWindow = driver.getWindowHandle();
//		LOGGER.info("------------- " + mainWindow);
//        Set<String> windows= driver.getWindowHandles();
//        if (windows.size() > 1) {
//            //first remove main window
//        	LOGGER.info(">>>>>>>>>> " + windows);
//            windows.remove(mainWindow);
//            //switching to new/child window and perform some action on new window if required. Then close it.
//            driver.switchTo().window(windows.iterator().next());
//            driver.close();
//            //switching back to mainWindow, Then continue with your actions.
//            driver.switchTo().window(mainWindow);
//            windows.clear();
//            
//        }
//		
		

		UserPage.CheckUserIsConnected();
		// If IC is not working an alert message is displayed

		// TODO
		// to check the user is connected check is name and firstName

	}

	@Then("^User is disconnected$")
	public void User_is_disconnected() throws Throwable {
		UserPage.CheckUserIsDisconnected();
	}

	@Given("^Tree is displayed$")
	public void Tree_is_displayed() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='tree_wrap']")));

		if (driver.findElement(By.xpath(".//*[@id='tree_wrap']")).isDisplayed()) {
			LOGGER.info("Tree is displayed");
		} else {
			LOGGER.severe("Tree is not Displayed");
			Assert.fail();
		}

	}

	@When("^User selects a site$")
	public void User_selects_a_site(List<Site> sites) throws Throwable {
		for (Site site : sites) {
			LOGGER.info("User has selected the site: " + site.getSitename());
			String xpath = ".//*[@id='" + site.getSitename() + "']/a/ins";
			UtilTools.clickOnButton(xpath);
		}
	}

	@When("^User selects patients$")
	public void User_selects_a_patient(List<Patient> patients) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 2);
		for (Patient patient : patients) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + patient.getCode() + "')]")));
			LOGGER.info("User is selecting the patient: " + patient.getCode());
			driver.findElement(By.xpath("//a[contains(text(), '" + patient.getCode() + "')]")).click();
		}
	}

	@When("^User selects a patient: \"([^\"]*)\"$")
	public void User_selects_a_patient(String patient) throws Throwable {

		long timeout = 2;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + patient + "')]")));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("timeout ('%d' s) to select patient='%s'", patient));
			Assert.fail();
		}
		LOGGER.info("User is selecting the patient: " + patient);
		driver.findElement(By.xpath("//a[contains(text(), '" + patient + "')]")).click();
	}

	@Then("^Patient is displayed in the tree$")
	public void Patient_is_displayed_in_the_tree(List<Patient> patients) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		for (Patient patient : patients) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + patient.getCode() + "')]")));
			LOGGER.info("Patient is displayed in the tree: " + patient.getCode());
		}

	}

	@Then("^Visit is displayed in the tree$")
	public void Visit_is_displayed_in_the_tree(List<Visit> visits) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 2);
		for (Visit visit : visits) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + visit.getCode() + "')]")));
			LOGGER.info("Visit is displayed in the tree: " + visit.getCode());
		}

	}

	@When("^User selects a visit: \"([^\"]*)\"$")
	public void User_selects_a_visit(String visit) throws Throwable {
		long timeout = 2;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + visit + "')]")));
		} catch (StaleElementReferenceException e) {

			LOGGER.severe(String.format("timeout ('%d' s) to select visit='%s'", visit));
			Assert.fail();
		}

		LOGGER.info("User is selecting the visit: " + visit);
		driver.findElement(By.xpath("//a[contains(text(), '" + visit + "')]")).click();
	}

	@When("^User selects visits")
	public void User_selects_a_visit(List<Visit> visits) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		for (Visit visit : visits) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + visit.getCode() + "')]")));
			LOGGER.info("User is selecting the visit: " + visit.getCode());
			driver.findElement(By.xpath("//a[contains(text(), '" + visit.getCode() + "')]")).click();
		}
	}

	@Then("^Basic QC form is displayed$")
	public void Basic_QC_form_is_displayed() throws Throwable {
		QC.checkQCForm();
	}

	@Given("^User accepts the QC form$")
	public void User_accepts_the_QC_form() throws Throwable {
		QC.acceptTheQCForm();
	}

	@Given("^User refused the QC form$")
	public void User_refused_the_QC_form() throws Throwable {
		QC.refusedTheQCForm();
	}

	@Given("^User asks a Corrective action for the QC$")
	public void User_asks_a_corrective_action_for_the_QC_form() throws Throwable {
		QC.askACorrectiveActionForTheQCForm();
	}

	@Given("^User set comments in the the QC form \"([^\"]*)\"$")
	public void User_set_comment_in_the_QC_form(String comment) throws Throwable {
		QC.setQCComment(comment);
	}

	@Then("^QC form is validated$")
	public void form_is_validated() throws Throwable {
		UtilTools.seFilterToAll();
		UtilTools.waitTreeIsDisplayed();
	}

	@When("^User sets filter to all$")
	public void User_sets_filter_to_all() throws Throwable {

		UtilTools.seFilterToAll();
		UtilTools.waitTreeIsDisplayed();
	}

	@Then("^QC was accepted for patient: \"([^\"]*)\" and visit: \"([^\"]*)\"$")
	public void QC_is_validated(String patient, String visit) throws Throwable {
		QC.qcWasAccepted(patient, visit);
	}

	@Then("^QC was refused for patient: \"([^\"]*)\" and visit: \"([^\"]*)\"$")
	public void QC_is_refused(String patient, String visit) throws Throwable {
		QC.qcWasRefused(patient, visit);
	}

	@Then("^QC was asked a corrective action for patient: \"([^\"]*)\" and visit: \"([^\"]*)\"$")
	public void QC_was_asked_a_corrective_action(String patient, String visit) throws Throwable {
		QC.qcWasAskedCorrectiveAction(patient, visit);
	}

	@Given("^Supervisor screen is displayed$")
	public void Supervisor_screen_is_displayed() throws Throwable {
		Supervision.SupervisorTableDetail();
	}

	@When("^Supervisors enters search parameter$")
	public void Supervisor_enters_search_parameter(List<SupervisorSearchData> supervisorSearchdatas) throws Throwable {

		for (SupervisorSearchData supervisorSearchdata : supervisorSearchdatas) {
			UtilTools.setSearchPatient(supervisorSearchdata.getPatient());
			UtilTools.setSearchVisit(supervisorSearchdata.getVisit());
			UtilTools.setSearchFrom(supervisorSearchdata.getFrom());
			UtilTools.setSearchTo(supervisorSearchdata.getTo());
			UtilTools.setSearchStatus(supervisorSearchdata.getStatus());
			LOGGER.info("Users searchs \npatient: " + supervisorSearchdata.getPatient() + "\n visit: "
					+ supervisorSearchdata.getVisit());
		}
	}

	@When("^User clicks on the Filter button$")
	public void User_clicks_on_the_Filter_button() throws Throwable {
		Supervision.SupervisorCliksOnTheFilterButton();
	}

	@When("^User Clicks on the first details button")
	public void User_clicks_on_the_first_details_button() throws Throwable {
		Supervision.SupervisorCliksOnTheDetailsButton();
	}

	@Then("^Resume page is displayed$")
	public void Resume_page_is_displayed(List<ResumeData> ResumeDatas) throws Throwable {

		for (ResumeData ResumeData : ResumeDatas) {
			Resume.ResumePageDetails(ResumeData.getPatient(), ResumeData.getVisit());
		}
	}

	@When("^Click on Results button$")
	public static void click_On_Results_button() throws Throwable {
		Details.UserClicksOnResultsBtn();
	}

	@Then("^Results page is displayed$")
	public static void Results_page_is_displayed(List<ResultsData> ResultsDatas) throws Throwable {

		for (ResultsData ResultsData : ResultsDatas) {
			Results.ResultsPageDetails(ResultsData.getPatient(), ResultsData.getVisit());
		}
	}

	@Then("^Accepted Quality control page is displayed$")
	public static void Accepted_Quality_control_page_is_displayed(List<QualityControlData> QualityControlDatas)
			throws Throwable {

		for (QualityControlData QualityControlData : QualityControlDatas) {
			QualityControl.AcceptedQCPageDetails(QualityControlData.patient, QualityControlData.visit);
		}
	}

	@Then("^Refused Quality control page is displayed$")
	public static void Refused_Quality_control_page_is_displayed(List<QualityControlData> QualityControlDatas)
			throws Throwable {

		for (QualityControlData QualityControlData : QualityControlDatas) {
			QualityControl.RefusedQCPageDetails(QualityControlData.patient, QualityControlData.visit);
		}
	}

	@Then("^Asked corrective action QC page is displayed$")
	public static void Asked_corrective_action_QC_page_is_displayed(List<QualityControlData> QualityControlDatas)
			throws Throwable {
		for (QualityControlData QualityControlData : QualityControlDatas) {
			QualityControl.AskedCorrectiveActionQCPageDetails(QualityControlData.patient, QualityControlData.visit);
		}
	}

	@When("^Click on Quality Control tab$")
	public static void Click_on_Quality_Control_tab() throws Throwable {
		Results.SupervisorClicksOnQualityControlTab();
	}

	@When("^Click on Investigation tab$")
	public static void Click_on_Investigation_tab() throws Throwable {
		Results.SupervisorClicksOnInvestigationTab();
	}

	@When("^Click on Reading tab$")
	public static void Click_on_Reading_tab() throws Throwable {
		Results.SupervisorClicksOnReadingTab();
	}
	
	@When("^Click on Adjudication tab$")
	public static void Click_on_Adjudication_tab() throws Throwable {
		Results.SupervisorClicksOnAdjudicationTab();
	}
		
	
	@Then("^INV form is displayed for \"([^\"]*)\" and \"([^\"]*)\"$")
	public static void INV_form_is_displayed(String Patient, String Visit) throws Throwable {
		pages.supervision.details.results.investigation.Investigation.PageDetails(Patient, Visit);
	}
	
	@Then("^Reading form is displayed for \"([^\"]*)\" and \"([^\"]*)\"$")
	public static void Reading_form_is_displayed(String Patient, String Visit) throws Throwable {
		pages.supervision.details.results.reading.Reading.PageDetailsWhen2Readings(Patient, Visit);
	}
	
	
	@Then("^Adjudication form is displayed for \"([^\"]*)\" and \"([^\"]*)\"$")
	public static void Adjudication_form_is_displayed(String Patient, String Visit) throws Throwable {
		pages.supervision.details.results.adjudication.Adjudication.PageDetails(Patient, Visit);
	}
	

	@When("^Click on delete QC button$")
	public static void Click_on_delete_QC_button() throws Throwable {
		UtilTools.clickOnButton(QualityControl.RESULTS_QC_DELETE_BTN_XPATH);
	}

	@When("^Click on delete INV button$")
	public static void Click_on_delete_INV_button() throws Throwable {
		UtilTools.clickOnButton(
				pages.supervision.details.results.investigation.Investigation.RESULTS_INV_DELETE_BTN_XPATH);
	}
	
	@When("^Click on delete Reading button$")
	public static void Click_on_delete_Reading_button() throws Throwable {
		UtilTools.clickOnButton(
				pages.supervision.details.results.reading.Reading.RESULTS_READING_SECOND_DELETE_BTN_XPATH);
	}
	
	@When("^Click on delete Adjudication button$")
	public static void Click_on_delete_Adjudication_button() throws Throwable {
		UtilTools.clickOnButton(
				pages.supervision.details.results.adjudication.Adjudication.RESULTS_ADJUDICATION_DELETE_BTN_XPATH);
	}

	@When("^Enter the reason comment \"([^\"]*)\"$")
	public static void Enter_the_reason_comment(String comment) throws Throwable {
		QualityControl.QCDeletedReason(comment);

	}

	
	@Then("^QC results page is empty$")
	public static void QC_results_page_is_empty() throws Throwable {
		click_On_Results_button();
		Results.SupervisorClicksOnQualityControlTab();
		if (QualityControl.CheckQCNotYetDone()) {
			LOGGER.info("QC is deleted");
		} else {
			LOGGER.severe("QC is NOT deleted");
			Assert.fail();
		}
	}

	@Then("^INV results page is empty$")
	public static void INV_results_page_is_empty() throws Throwable {
		click_On_Results_button();
		Results.SupervisorClicksOnInvestigationTab();
		if (pages.supervision.details.results.investigation.Investigation.CheckQCNotYetDone()) {
			LOGGER.info("Upload is deleted");
		} else {
			LOGGER.severe("Upload is NOT deleted");
			Assert.fail();
		}
	}
	
	@Then("^Second Reading form results is deleted$")
	public static void Reading_results_page_is_empty() throws Throwable {
		click_On_Results_button();
		Results.SupervisorClicksOnReadingTab();
		if (pages.supervision.details.results.reading.Reading.checkSecondReadingFormIsDeleted()) {
			LOGGER.info("Second reading form is deleted");
		} else {
			LOGGER.severe("Second reading form is NOT deleted");
			Assert.fail();
		}
	}
	
	
	@Then("^Adjudication form results is deleted$")
	public static void Adjudication_results_page_is_empty() throws Throwable {
		click_On_Results_button();
		Results.SupervisorClicksOnAdjudicationTab();
		if (pages.supervision.details.results.adjudication.Adjudication.CheckNotYetDone()) {
			LOGGER.info("Adjudication form is deleted");
		} else {
			LOGGER.severe("Adjudication form is NOT deleted");
			Assert.fail();
		}
	}
	
	
	
	

	@Given("^Default filter is displayed$")
	public static void Default_filter_is_displayed() throws Throwable {
		UtilTools.checkDefaultFilterIsDisplayed();
	}

	@When("^Change filter to all$")
	public static void Change_filter_to_all() throws Throwable {
		UtilTools.seFilterToAll();
	}

	@Then("^All filter is displayed$")
	public static void All_filter_is_displayed() throws Throwable {
		UtilTools.checkAllFilterIsDisplayed();
	}

	@Given("^Controller tab is displayed$")
	public static void Controller_tab_is_displayed() throws Throwable {
		UserPage.CheckControllerTabIsDisplayed();
	}

	@Given("^Reader tab is displayed$")
	public static void Reader_tab_is_displayed() throws Throwable {
		UserPage.CheckReaderTabIsDisplayed();
	}
	
	@Given("^Adjudicator tab is displayed$")
	public static void Adjudication_tab_is_displayed() throws Throwable {
		UserPage.CheckAdjudicatorTabIsDisplayed();
	}

	@Then("^User clicks on the controller tab$")
	public static void Users_Cliks_on_the_controller_tab() throws Throwable {
		scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
		UserPage.ClickOntheControllerTab();
	}

	@Then("^User clicks on the reader tab$")
	public static void User_clicks_on_the_reader_tab() throws Throwable {
		scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
		UserPage.ClickOntheReaderTab();
	}

	@Then("^User clicks on the Adjudicator tab$")
	public static void User_clicks_on_the_Adjudicator_tab() throws Throwable {
		scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
		UserPage.ClickOntheAdjudicatorTab();
	}
	
	
	
	
	@Given("^Supervisor tab is displayed$")
	public static void Supervisor_tab_is_displayed() throws Throwable {
		UserPage.CheckSupervisorTabIsDisplayed();
	}

	@Then("^User clicks on the Supervisor tab$")
	public static void Users_Cliks_on_the_supervisor_tab() throws Throwable {
		scenario.write(scenario.getName() + ": " + Thread.currentThread().getStackTrace()[1].getMethodName());
		UserPage.ClickOntheSupervisorTab();
	}

	@Then("^Controller screen is displayed$")
	public static void Controller_tree_is_displayed() throws Throwable {
		// TODO
		// menu tab is selected: in blue
		// Patient tree is displayed
		LOGGER.info("Controller screen is displayed");
	}

	@Then("^Reader screen is displayed$")
	public static void Reader_tree_is_displayed() throws Throwable {
		// TODO
		// menu tab is selected: in blue
		// Patient tree is displayed
		LOGGER.info("Reader screen is displayed");
	}
	
	@Then("^Adjudicator screen is displayed$")
	public static void Adjudicator_tree_is_displayed() throws Throwable {
		// TODO
		// menu tab is selected: in blue
		// Patient tree is displayed
		LOGGER.info("Adjudicator screen is displayed");
	}
	
	

	@Then("^Basic Upload form is displayed$")
	public static void Basic_Upload_form_is_displayed() throws Throwable {
		Investigation.checkINVForm();
	}

	@Then("^Basic Reading form is displayed$")
	public static void Basic_Reading_form_is_displayed() throws Throwable {
		Reading.CheckForm();
	}
	
	@Then("^Basic Adjudication form is displayed$")
	public static void Basic_Adjudicator_form_is_displayed() throws Throwable {
		Adjudication.checkForm();
	}
	

	@When("^User validates the INV form$")
	public static void User_validates_the_INV_form() throws Throwable {
		Investigation.ValidateTheInvForm();
	}

	@When("^User validates the Reading form$")
	public static void User_validates_the_reader_form() throws Throwable {
		Reading.ValidateTheForm();
	}
	
	@When("^User validates the Adjudication form$")
	public static void Adjudication_validates_the_Adjudication_form() throws Throwable {
		Adjudication.ValidateTheForm();
	}

	@Then("^Reading form is validated for patient: \"([^\"]*)\" and visit: \"([^\"]*)\"$")
	public static void reading_form_is_validated(String patient, String visit) throws Throwable {
		UtilTools.seFilterToAll();
		Reading.FormIsValidated(patient, visit);
	}

	@Then("^Adjudication form is validated$")
	public static void adjudication_form_is_validated() throws Throwable {
		UtilTools.seFilterToAll();
		Adjudication.FormIsValidated();
	}
	
	@Then("^INV form is validated$")
	public static void INV_form_is_validated() throws Throwable {
		Investigation.InvestigationformIsValidated();

	}

	@Then("^\"([^\"]*)\" and \"([^\"]*)\" are still displayed in the tree$")
	public static void Patient_and_Visit_are_still_displayed_in_the_tree(String Patient, String Visit)
			throws Throwable {
		String xpath = ".//*[@title='" + Patient + "']/ul[1]/li[1]/a[1]";
		if (UtilTools.checkLabelIsDisplayed(xpath, Visit))
			LOGGER.info(String.format("Patient: '%s' and visit '%s' are still present in the tree", Patient, Visit));
		else {
			LOGGER.info(String.format("Patient: '%s' and visit '%s' ARE NOT present in the tree", Patient, Visit));
			Assert.fail();
		}
	}

	@Then("^\"([^\"]*)\" and \"([^\"]*)\" are not in the tree$")
	public static void Patient_and_Visit_are_not_displayed_in_the_tree(String Patient, String Visit) throws Throwable {
		String xpath = ".//*[@title='" + Patient + "']/ul[1]/li[1]/a[1]";
		if (UtilTools.checkLabelContentsNotDisplayed(xpath, Visit))
			LOGGER.info(String.format("Patient: '%s' and visit '%s' are still present in the tree", Patient, Visit));
		else {
			LOGGER.info(String.format("Patient: '%s' and visit '%s' ARE NOT present in the tree", Patient, Visit));
			Assert.fail();
		}
	}

	
	@When("^User validates the Form deletion$")
	public static void User_validates_the_form_deletion() throws Throwable {
		UtilTools.clickOnButton(Entity.REASON_DELETED_ENTITY_OK_BTN_XPATH);
	}

}
