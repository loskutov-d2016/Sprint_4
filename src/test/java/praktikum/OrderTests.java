package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MainPage;
import pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class OrderTests {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderDuration;
    private final String orderComment;

    public OrderTests(String name, String surname, String address, String metroStation, String phone, String orderDuration, String orderComment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.orderDuration = orderDuration;
        this.orderComment = orderComment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Петр", "Иванов", "Москва, Красная площадь", "Бульвар Рокоссовского", "+79991234567", "двое суток", "126"},
                {"Анна", "Сидорова", "Москва, ул. Волкова 61", "Черкизовская", "+79997654321", "трое суток", "127"}
        });
    }

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @Test
    public void testOrderCreation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        orderPage.fillSecondOrderForm(orderDuration, orderComment);

        orderPage.confirmOrder();

        Assert.assertTrue(orderPage.isOrderSuccessModalDisplayed(), "Success modal is not displayed");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}