package praktikum;

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

    @Test
    public void testOrderCreation() {
//         Открытие главной страницы
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

//         Выбор кнопки "Заказать" в зависимости от типа
        mainPage.clickOrderButton(orderButtonType);

        // Заполнение формы заказа
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        orderPage.fillSecondOrderForm(orderDuration, orderComment);

        // Подтверждение заказа
        orderPage.confirmOrder();

        // Проверка успешного отображения модального окна
        Assert.assertTrue(orderPage.isOrderSuccessModalDisplayed(), "Success modal is not displayed");
    }
}