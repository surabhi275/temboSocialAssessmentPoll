package googleForms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commonUtilities.Utilities;

public class PollSubmission {

	// This is a generic function that accepts a question element
	// Accepts a question and then makes the selection
	// This function is called after a type of question is already determined as dropdown
	public static void dropdownTypeQuestion(WebElement question) {

		
		WebElement dropdown = Utilities.driver.findElement(By.xpath("//div[@class='ps-answer-dropdown']/select"));
		Select ques = new Select(dropdown);
		ques.selectByVisibleText("Answer A");

	}
	// This function currently is hard coded but 
	// Target is to use a Question ID and then use the QuestionID as parent element and
	// identify the type of question by looking at elements under it
	public static void radioButtonTypeQuestion(WebElement question) {
		WebElement radiobButton = Utilities.driver.findElement(
				By.xpath("//div[@role='radiogroup']/div[@aria-labelledby='ps_answer_PSPoll_268840-prompt']"));

		radiobButton.click();
	}

	public static void iterateOverThePollPages() {
		// Keep iterating over the form till the acknowledgement is present on the page
		// i.e. iterate till poll is complete
		while (!isThisFinalAcknowledgementPage()) {
			// Get Questions For Current Page
			List<WebElement> questions = Utilities.getQuestionsOfCurrentPage();
			System.out.println("Iterating over page questions");
			for (int i = 0; i < questions.size(); i++) {
				answerTheQuestion(questions.get(i));
			}
			//In actual case we would not have break condition
			break;
		}
	}
	// Make the click event call to give answer for the given question by first identifying the type of question
	// then 
	public static void answerTheQuestion(WebElement question) {
		int type = identifyQuestionType(question);
		System.out.println("Identified Type as "+type);
		if(type==1) {
			radioButtonTypeQuestion(question);
			
		}else if(type==2) {
			dropdownTypeQuestion(question);
		}
	}

	// Identify the type of the Question e.g. Image Radio button, Rating , dropdown
	public static int identifyQuestionType(WebElement question) {
		int type=0;
		// ** This function is incomplete due to lack of time I would identify
		// conditions to identify a type of question
		// By listing conditions and coding them to identify each type
		// E.g. Static dropdown would have the html tagname <select>.
		
		// Hard coded for time being due to lack of time
		String questionID = question.getAttribute("id");
		System.out.println("Identifying Type of Question:"+questionID);
		if (questionID.equals ("question-79364")) {
			type = 1;//Radio Button 
		} else if (questionID.equals ("question-79363")) {
			type = 2;//Dropdown
		} else if (questionID.equals ("question-79362")) {
			type = 3;//Image radio buttons
		} // other types of questions including the rating are needed to reach the final page
		
		return type;
	}

	// Function to check if poll is complete
	public static boolean isThisFinalAcknowledgementPage() {
		List<WebElement> acknowledgement = Utilities.driver.findElements(By.xpath("//div[@class='ps-acknowledgement']"));
		
		if (acknowledgement.size() > 0) {
			// Condition is true if final page has been reached and poll is complete
			return true;

		} else {
			// Condition is false if final page has not been reached yet
			return false;
		}

	}
}
