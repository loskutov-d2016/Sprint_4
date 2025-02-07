package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testng.Assert;
import pages.MainPage;
import pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class OrderTests extends BaseTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderDuration;
    private final String orderComment;
    private final String orderButtonType;

    private MainPage mainPage;
    private OrderPage orderPage;

    public OrderTests(String name, String surname, String address, String metroStation, String phone,
                      String orderDuration, String orderComment, String orderButtonType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.orderDuration = orderDuration;
        this.orderComment = orderComment;
        this.orderButtonType = orderButtonType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Петр", "Иванов", "Москва, Красная площадь", "Бульвар Рокоссовского", "+79991234567", "двое суток", "126", "top"},
                {"Анна", "Сидорова", "Москва, ул. Волкова 61", "Черкизовская", "+79997654321", "трое суток", "127", "bottom"}
        });
    }

    @Before
    public void setUpTest() {
        // Открытие главной страницы
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        // !!!МОЖНО СДЕЛАТЬ ТАК. вынес в тест, т.к возможн будет инои тестовый сценарии!!!
//        mainPage.clickOrderButton(orderButtonType);
//        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderCreation() {
        // Выбор кнопки "Заказать" в зависимости от типа
        mainPage.clickOrderButton(orderButtonType);
        // Инициализация страницы заказа
        orderPage = new OrderPage(driver);
        // Заполнение формы заказа
        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        orderPage.fillSecondOrderForm(orderDuration, orderComment);
        // Подтверждение заказа
        orderPage.confirmOrder();
        // Проверка успешного отображения модального окна
        Assert.assertTrue(orderPage.isOrderSuccessModalDisplayed(), "Success modal is not displayed");
    }
}