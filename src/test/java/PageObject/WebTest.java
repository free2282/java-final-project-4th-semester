package PageObject;
import Locators.Locators;
import PageObject.MainPage.MainPage;
import PageObject.OrderPage.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class WebTest
{

    private WebDriver chDriver;
    private WebDriver frDriver;
    private Locators locators;
    private MainPage TestMainPage;
    private OrderPage TestOrderPage;
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    private String orderUrl = "https://qa-scooter.praktikum-services.ru/order";


    @Test
    public void checkScooterRentPositeve()
    {

        TestOrderPage.clickElement(locators.returnLocatorsButtonMainPage()[0]);


        for (int i=0;i<4;i++)
        {
            TestOrderPage.putTextToInput(locators.returnInputLocateTextOfOrder()[i], TestOrderPage.returnFirstTestOrderData()[i]);
            //заполняем имя, фамилия, улица, метро, телефон.
        }
        TestOrderPage.putTextToInput(locators.returnInputLocateTextOfOrder()[4], TestOrderPage.returnFirstTestOrderData()[4]); // вставляем станцию
        TestOrderPage.waitMetroVisible(locators.returnInputLocateTextOfOrder()[7]);
        TestOrderPage.clickElement(locators.returnInputLocateTextOfOrder()[7]);

        TestOrderPage.clickElement(locators.returnLocateButtonOnPageOrder()[1]);

        for (int i=5;i<7;i++)
        {
            TestOrderPage.putTextToInput(locators.returnInputLocateTextOfOrder()[i], TestOrderPage.returnFirstTestOrderData()[i]);
            //заполнение когда привезти и комментарий
        }

        TestOrderPage.clickElement(locators.returnInputLocateTextOfOrder()[9]); // заполнение срока аренды
        TestOrderPage.waitLoadingElement(locators.returnInputLocateTextOfOrder()[12]);
        TestOrderPage.clickElement(locators.returnInputLocateTextOfOrder()[12]);

        TestOrderPage.clickElement(locators.returnInputLocateTextOfOrder()[10]);
        TestOrderPage.clickElement(locators.returnInputLocateTextOfOrder()[11]);

        TestOrderPage.clickElement(locators.returnLocateButtonOnPageOrder()[0]);//Кнопка заказать
        TestOrderPage.clickElement(locators.returnLocateButtonOnPageOrder()[2]);//Кнопка Да

        chDriver.findElement(locators.returnLocateTextOfOrdering());

    }

    @Before
    public void precondition()
    {
        WebDriverManager.chromedriver().setup();
        chDriver = new ChromeDriver();
        chDriver.get(mainPageUrl);
        locators = new Locators();
        TestMainPage = new MainPage(chDriver);
        TestOrderPage = new OrderPage(chDriver);
        chDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        TestMainPage.clicklement(locators.returnLocatorCoockie());//кликаем на куки, чтобы оно не мешало

    }
    @After
    public void tearDown()
    {
        chDriver.quit();
    }

}
