package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private final WebDriver driver;

    private final By topOrderButton = By.xpath("//*[@class='Button_Button__ra12g']");

    private final By importantQuestionsSection = By.className("accordion");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public QuestionsPage openImportantQuestionsSection() {
        driver.findElement(importantQuestionsSection).isDisplayed();
        return new QuestionsPage(driver);
    }
}