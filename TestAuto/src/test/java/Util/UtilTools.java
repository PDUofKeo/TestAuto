package Util;

import java.util.List;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testauto.SharedDriver;

public class UtilTools extends SharedDriver {

	public static RemoteWebDriver driver = getDriver();

	public static int TIMEOUT = 10;

	private final static Logger LOGGER = Logger.getLogger(UtilTools.class.getName());
	private final static String FILTER_XPATH = ".//*[@id='select_filter']";
	private final static String DEFAULT_FILTER_LABEL = "default";
	private final static String ALL_FILTER_LABEL = "all";

	public static boolean checkFieldIsDisplayed(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("timeout ('%d' s) reached for xpath='%s'", TIMEOUT, xpath));
			Assert.fail();
		}

		LOGGER.info("The field: " + xpath + " is displayed");
		return true;

	}

	public static boolean checkLabelIsDisplayed(String xpath, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(
					String.format("timeout ('%d' s) reached for xpath='%s' and label='%s'", TIMEOUT, xpath, label));
			Assert.fail();
		}

		String elementLabel = "";
		// To avoid StaleElementReferenceException
		try {
			LOGGER.info(String.format("Find the following xpath: '%s'", xpath));
			WebElement WebElt = retryingFindElement(By.xpath(xpath));
			if (null != WebElt)
				elementLabel = WebElt.getText();

		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("StaleElementReferenceException: '%s'", e.getMessage()));
			return false;
		}

		if (elementLabel.contains(label)) {
			LOGGER.info(String.format("Success: search String '%s' == founded string '%s' for xpath='%s' ",
					label, elementLabel, xpath));
			return true;
		} else {
			LOGGER.severe(String.format(
					"Failed: Search String '%s' != founded string '%s' for xpath:'%s' ", label,
					elementLabel, xpath));
			return false;
		}
	}

	public static boolean checkLabelContentsNotDisplayed(String xpath, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			LOGGER.info(String.format("ELEMENT IS NO MORE VISIBLE: xpath='%s'", xpath));

		} catch (StaleElementReferenceException e) {
			LOGGER.severe(
					String.format("Check Invisibility : timeout ('%d' s) reached for xpath='%s'", TIMEOUT, xpath));
			return false;
		}

		LOGGER.info("The element: " + label + " is no more displayed");
		return true;
	}

	public static boolean checkDefaultFilterIsDisplayed() {
		return checkLabelIsDisplayed(FILTER_XPATH, DEFAULT_FILTER_LABEL);
	}

	public static boolean checkAllFilterIsDisplayed() {
		return checkLabelIsDisplayed(FILTER_XPATH, ALL_FILTER_LABEL);
	}

	public static boolean seFilterToAll() {

		return setFilter("2");
	}

	public boolean seFilterToDefault() {
		return setFilter("1");
	}

	public static boolean setFilter(String value) {
		return setSeletedValue(FILTER_XPATH, value);
	}

	
	public static boolean setSeletedValue(String xpath, String value) {

		long timeout = 2;
		try {
			WebDriverWait waitTreeLoading = new WebDriverWait(driver, timeout);
			waitTreeLoading.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		} catch (Exception e) {
			LOGGER.severe(String.format("timeout ('%d' s) reached for xpath='%s'", timeout, xpath));
			return false;
		}
		WebElement mySelectElm = driver.findElement(By.xpath(xpath));
		Select mySelect = new Select(mySelectElm);
		mySelect.selectByValue(value);

		return true;
	}
	
	
	public static boolean waitTreeIsDisplayed() {
		String loader = ".//*[@id='loader']";
		long timeout = 2;

		try {
			WebDriverWait waitTreeLoading = new WebDriverWait(driver, timeout);
			waitTreeLoading.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loader)));

		} catch (Exception e) {
			LOGGER.severe(String.format("Visibility: Timeout ('%d' s) reached for xpath='%s'", timeout, loader));
			return false;
		}
		LOGGER.info(String.format("Check VISIBILITY for following xpath='%s'", loader));

		try {

			WebDriverWait waitTreeLoaded = new WebDriverWait(driver, timeout);
			waitTreeLoaded.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loader)));

		} catch (Exception e) {
			String.format("Invisibility: Timeout ('%d' s) reached for xpath='%s'", timeout, loader);
			// Assert.fail();
		}
		LOGGER.info(String.format("Check INVISIBILITY for following xpath='%s'", loader));
		return true;

	}

	public static void setSearchPatient(String searchValue) {
		setInputFieldTextValue(".//*[@id='supervision_supervisorRowFilter_filterPatient']", searchValue);
	}

	public static void setSearchVisit(String searchValue) {
		setInputFieldTextValue(".//*[@id='supervision_supervisorRowFilter_filterVisit']", searchValue);
	}

	public static void setSearchFrom(String searchValue) {
		setInputFieldTextValue(".//*[@id='supervision_supervisorRowFilter_filterUploadDateStart']", searchValue);
	}

	public static void setSearchTo(String searchValue) {
		setInputFieldTextValue(".//*[@id='supervision_supervisorRowFilter_filterUploadDateEnd']", searchValue);
	}

	public static void setSearchStatus(String searchValue) {
		WebElement mySelectElm = driver.findElement(By.id("supervision_supervisorRowFilter_filterStatus"));
		Select mySelect = new Select(mySelectElm);
		mySelect.selectByVisibleText(searchValue);
	}

	/**
	 * Set text value in the input field
	 * 
	 * @param driver:
	 *            web driver
	 * @param xpath:
	 *            Xpath of the web element
	 * @param value:
	 *            To set in the input field
	 */
	public static boolean setInputFieldTextValue(String xpath, String value) {

		long timeout = 1;

		try {
			// wait until supervisor table is displayed
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		} catch (Exception e) {
			LOGGER.severe(String.format("Timeout ('%d' s) reached for xpath='%s'", timeout, xpath));
			return false;
		}

		driver.findElement(By.xpath(xpath)).sendKeys(value);
		LOGGER.info(String.format("Set following value: '%s' in the following field xpath='%s'", value, xpath));
		return true;

	}

	public static boolean clickOnButton(String xpath) {

		try {
			// wait until supervisor table is displayed
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			LOGGER.severe(String.format("timeout: '%d' s reached for xpath:'%s'", TIMEOUT, xpath));
			Assert.fail();
			return false;
		}
		try {
			LOGGER.info(String.format("Click on the following xpath: '%s'", xpath));
			retryingFindClick(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("StaleElementReferenceException: '%s'", e.getMessage()));
			return false;
		}
		return true;
	}

	public static boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 0;

		while (attempts < 2) {
			try {
				driver.findElement(by).click();
				LOGGER.info(String.format("retrying find click N°: '%d' for xpath: %s", attempts, by.toString()));
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
				LOGGER.severe(String.format("StaleElementReferenceException: '%s'", e.getMessage()));
			}
			attempts++;
		}
		return result;
	}

	// To avoid StaleElementReferenceException
	public static WebElement retryingFindElement(By by) {
		WebElement result = null;
		int attempts = 0;

		while (attempts < 2) {
			try {
				result = driver.findElement(by);
				LOGGER.info(String.format("retrying find click N°: '%d' for xpath: %s", attempts, by.toString()));
				break;
			} catch (StaleElementReferenceException e) {
				LOGGER.severe(String.format("StaleElementReferenceException: '%s'", e.getMessage()));
			}
			attempts++;
		}
		return result;
	}

	public void clickLinkByHref(String href) {
		List<WebElement> anchors = driver.findElements(By.xpath(".//*[@href='#tab-qc']"));
		LOGGER.info("icci " + anchors.size());

		for (WebElement anchor : anchors) {
			LOGGER.info("anchor : " + anchor.getTagName());
			LOGGER.info("anchor : " + anchor.getText());
			LOGGER.info("is display : " + anchor.isDisplayed());
			LOGGER.info("is enabled: " + anchor.isEnabled());
			anchor.click();
			/*
			 * if(anchor.getAttribute("href").contains(href)) { anchor.click();
			 * break; }
			 */
		}
	}

	public void getiFrameList() {
		List<WebElement> Frames = driver.findElements(By.tagName("iframe"));
		LOGGER.info("iFrame count " + Frames.size());
		for (WebElement frame : Frames) {
			LOGGER.info("frame tagname: " + frame.getTagName());
			LOGGER.info("frame text : " + frame.getText());
		}
	}

	public static boolean checkImageIsDisplayed(String xpath, String ImgStyle) {

		LOGGER.info("xpath : " + "'" + xpath + "'");
		LOGGER.info("ImgStyle : " + "'" + ImgStyle + "'");

		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("Timeout ('%d' s) reached for xpath='%s'", TIMEOUT, xpath));
			return false;
		}

		// To avoid StaleElementReferenceException
		WebElement WebElt = null;
		try {
			LOGGER.info(String.format("Find the following xpath: '%s'", xpath));
			WebElt = retryingFindElement(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			LOGGER.severe(String.format("StaleElementReferenceException: '%s'", e.getMessage()));
			return false;
		}

		String img = WebElt.getAttribute("style").toString();

		if (img.equals(ImgStyle)) {
			LOGGER.info(String.format("Success: The style '%s' of the image xpath '%s' is DISPLAYED", ImgStyle, xpath));
			return true;
		} else {
			LOGGER.severe(String.format(
					"Failed: The searched style '%s' of the image xpath '%s' IS NOT DISPLAYED. The finded string is : '%s'",
					ImgStyle, xpath, img));
			return false;
		}
	}

}
