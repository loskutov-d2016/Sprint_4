package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MainPage;
import pages.QuestionsPage;


public class QuestionsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @Test
    public void testAllQuestionsOpenAnswers() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        QuestionsPage questionsPage = mainPage.openImportantQuestionsSection();
        questionsPage.waitForPageToLoad();

        String[] expectedTexts = {
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями," +
                        " можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня." +
                        " Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру." +
                        " Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете " +
                        "кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        };

        for (int i = 0; i < expectedTexts.length; i++) {
            try {
                questionsPage.clickQuestion(i);
                Assert.assertTrue(questionsPage.isAnswerVisible(i), "Ответ на вопрос " + (i + 1) + " не отображается");
                Assert.assertTrue(questionsPage.isAnswerTextCorrect(i, expectedTexts[i]), "Текст ответа на вопрос " + (i + 1) + " не соответствует ожидаемому");
            } catch (Exception e) {
                Assert.fail("Test failed for question " + (i + 1) + ": " + e.getMessage());
            }
        }
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}