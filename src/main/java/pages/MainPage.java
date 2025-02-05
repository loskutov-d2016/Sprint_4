package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By topOrderButton = By.xpath("(//button[text()='Заказать'])[1]");
    private final By orderButton = By.xpath("(//button[text()='Заказать'])[2]");
    private final By importantQuestionsSection = By.className("accordion");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickOrderButton(String buttonType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button;

        if ("top".equals(buttonType)) {
            button = wait.until(ExpectedConditions.elementToBeClickable(topOrderButton));
        } else if ("bottom".equals(buttonType)) {
            button = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        } else {
            throw new IllegalArgumentException("Unknown order button type: " + buttonType);
        }
        // Прокрутка к элементу перед кликом
        new Actions(driver).moveToElement(button).perform();
        button.click();
    }

    public QuestionsPage openImportantQuestionsSection() {
        if (driver.findElement(importantQuestionsSection).isDisplayed()) {
            return new QuestionsPage(driver);
        } else {
            throw new IllegalStateException("Important questions section is not displayed.");
        }
    }
}