package commonUtilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	// Reusable variables and functions are declared in this class
	public static WebDriver driver;
	public static String ques = "//fieldset/div[starts-with(@id,'question-')]";
	public static String validationMessage = "Please select an option.";
	public static String submitButtonByNameString = "vote_button";
	public static String submitButtonXpathString = "//input[@name='vote_button']";
	public static int timeout = 10;

	public static WebDriver chromeDriverSetup() {

	//Generic method to initiate chrome driver and setting global timeouts
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	//Generic method to click on Continue and Submit buttons
	public static void clickOnSubmit(WebDriver driver, String submitButton, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);		
		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable((By.name(submitButton))));
		submit.click();
	}

	// Generic method to get all the Questions on a page
	public static List<WebElement> getQuestionsOfCurrentPage() {
		List<WebElement> questions = driver.findElements(By.xpath(ques));
		return questions;
	}
}
