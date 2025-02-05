package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private final WebDriver driver;

    // Локаторы для первой страницы формы
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationDropdown = By.className("select-search__input");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");

    // Локаторы для второй страницы формы
    private final By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By calendar = By.xpath("//*[@class='react-datepicker__day react-datepicker__day--013']");
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By scooterСolor = By.xpath("//div[@class='Order_Checkboxes__3lWSI']");
    private final By scooterСolorBlack = By.id("black");
    private final By orderConfirmationButton = By.xpath("(//*[text()='Заказать'])[2]");
    private final By orderConfirmationButtonYes = By.xpath("//*[text()='Да']");
    private final By orderHasBeenPlaced = By.xpath("//*[text()='Заказ оформлен']");
    private final By successModal = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение первой страницы формы
    public void fillOrderForm(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationDropdown).click();
        driver.findElement(By.xpath("//div[text()='" + metroStation + "']")).click(); // Выбор станции метро
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Заполнение второй страницы формы
    public void fillSecondOrderForm(String rentalPeriod, String comment) {
        driver.findElement(deliveryDateField).click();
        driver.findElement(calendar).click();
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-menu']/div[text()='" + rentalPeriod + "']")).click();
        driver.findElement(scooterСolor).isDisplayed();
        driver.findElement(scooterСolorBlack).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderConfirmationButton).click();
        driver.findElement(orderConfirmationButtonYes).click();
    }

    // Проверка успешного завершения заказа
    public boolean isOrderSuccessModalDisplayed() {
        return driver.findElement(successModal).isDisplayed();
    }

    public void confirmOrder() {
        driver.findElement(orderHasBeenPlaced).isDisplayed();
    }
}