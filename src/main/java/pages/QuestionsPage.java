package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuestionsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By questionsLocator = By.xpath("//div[@data-accordion-component='AccordionItemHeading']");
    private final By answersLocator = By.xpath("//div[@data-accordion-component='AccordionItemPanel']");
    private final By importantQuestionsSection = By.className("accordion");

    public QuestionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(questionsLocator));
    }

    public void clickQuestion(int index) {
        List<WebElement> questions = driver.findElements(questionsLocator);
        if (index >= 0 && index < questions.size()) {
            WebElement question = questions.get(index);
            wait.until(ExpectedConditions.elementToBeClickable(question));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question); // Scroll to the question
            question.click();
        } else {
            throw new IndexOutOfBoundsException("Question index out of bounds: " + index);
        }
    }

    public boolean isAnswerVisible(int index) {
        List<WebElement> answers = driver.findElements(answersLocator);
        if (index >= 0 && index < answers.size()) {
            WebElement answer = answers.get(index);
            wait.until(ExpectedConditions.visibilityOf(answer));
            return answer.isDisplayed();
        }
        return false;
    }

    public boolean isAnswerTextCorrect(int index, String expectedText) {
        List<WebElement> answers = driver.findElements(answersLocator);
        if (index >= 0 && index < answers.size()) {
            WebElement answer = answers.get(index);
            wait.until(ExpectedConditions.visibilityOf(answer));
            return answer.getText().contains(expectedText);
        }
        return false;
    }

    public QuestionsPage openImportantQuestionsSection() {
        if (driver.findElement(importantQuestionsSection).isDisplayed()) {
            return new QuestionsPage(driver);
        } else {
            throw new IllegalStateException("Important questions section is not displayed.");
        }
    }
}