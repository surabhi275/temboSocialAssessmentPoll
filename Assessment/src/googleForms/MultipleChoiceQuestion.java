package googleForms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.Utilities;

public class MultipleChoiceQuestion {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = Utilities.chromeDriverSetup();

		// navigation to published poll
		driver.get("https://stage1-vote.pollstream.com/8610");

		// click on Continue button
		Utilities.clickOnSubmit(driver, Utilities.submitButtonByNameString, Utilities.timeout);

		// click on Submit button without entering any input
		Utilities.clickOnSubmit(driver, Utilities.submitButtonByNameString, Utilities.timeout);

		// Verification of validation messages
		ValidationErrors.TestCaseStep2();

		// Uncomment to run the page selection
		PollSubmission.iterateOverThePollPages();

		driver.quit();

	}

}
