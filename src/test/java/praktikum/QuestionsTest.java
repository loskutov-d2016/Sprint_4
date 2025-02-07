package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import pages.MainPage;
import pages.OrderPage;
import pages.QuestionsPage;


public class QuestionsTest extends BaseTest {

    private MainPage mainPage;
    private QuestionsPage questionsPage;

    @Before
    public void setUpTest() {
        // Открытие главной страницы
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        // !!!МОЖНО СДЕЛАТЬ ТАК. вынес questionPage в тест, т.к возможн будет инои тестовый сценарии!!!
//        questionsPage = mainPage.openImportantQuestionsSection();
//        questionsPage.waitForPageToLoad();
    }

    @Test
    public void testAllQuestionsOpenAnswers() {
        questionsPage = mainPage.openImportantQuestionsSection();
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
            questionsPage.clickQuestion(i);

            // Проверка, что ответ отображается
            boolean isAnswerVisible = questionsPage.isAnswerVisible(i);
            Assert.assertTrue(isAnswerVisible, "Ответ на вопрос " + (i + 1) + " не отображается");

            // Проверка, что текст ответа корректен
            boolean isAnswerTextCorrect = questionsPage.isAnswerTextCorrect(i, expectedTexts[i]);
            Assert.assertTrue(isAnswerTextCorrect, "Текст ответа на вопрос " + (i + 1) + " не соответствует ожидаемому");
        }
    }
}