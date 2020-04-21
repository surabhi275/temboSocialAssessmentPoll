package googleForms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonUtilities.Utilities;

public class ValidationErrors {

	//This replicates the case where a submit button was clicked without any selection
	// An error message should be displayed for all questions and the validation
	// text should match
	public static void TestCaseStep2() {

		List<WebElement> questions = Utilities.getQuestionsOfCurrentPage();
		boolean step2Result = ValidationErrors.verifyAllValidationMessageDisplayed(questions);

		if (step2Result == true) {
			System.out.println("Validation errors displayed successfully");
		} else {
			System.out.println("Validation errors are not displayed as expected");
		}
	}
	
	// Iterate over the list of Questions on the page and verify validation messages displayed for all
	public static boolean verifyAllValidationMessageDisplayed(List<WebElement> questions) {
		String questionID = null;
		for (int i = 0; i < questions.size(); i++) {
			questionID = questions.get(i).getAttribute("id");
			//Retrieve question ID for each question element
			boolean isValidationMessageDisplayed = verifyIfQuestionHasValidationMessage(questionID);
			if (isValidationMessageDisplayed == false) {
				return false;
			}
		}
		return true;

	}
	
	//Validate if validation message is displayed for a given Question
	public static boolean verifyIfQuestionHasValidationMessage(String questionID) {
		//Prepare xPath for the validation message for a given Question ID
		String validationXpathString = "//fieldset/div[starts-with(@id," + questionID
				+ ")]/div[@class='ps-validation-message' and @role='alert']";
		//Verify if the Validation message is present with role alert i.e. its visible
		int validationMessagesCount = Utilities.driver.findElements(By.xpath(validationXpathString)).size();
		if (validationMessagesCount == 0) {
			return false;
		} else {
			//Compare and validate message as expected
			String validationMessage = Utilities.driver.findElement(By.xpath(validationXpathString)).getText();
			if (validationMessage.toUpperCase().equals(Utilities.validationMessage.toUpperCase())) {
				return true;
			}else {
				return false;
			}
		}
	}

}
