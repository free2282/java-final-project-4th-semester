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
public class OrderPageTest
{
    private WebDriver chDriver;
    private Locators locators;
    private MainPage TestMainPage;
    private OrderPage TestOrderPage;
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    private String orderUrl = "https://qa-scooter.praktikum-services.ru/order";


    @Test
    public void checkScooterRentPositeve()
    {
        for (int i=0;i<2;i++)
        {

            TestOrderPage.clickElement(locators.getLocatorsButtonMainPage()[0]);


            for (int j=0;j<4;j++)
            {
                TestOrderPage.putTextToInput(locators.getInputLocateTextOfOrder()[j], TestOrderPage.getDataForOrderTest()[i][j]);
                //заполняем имя, фамилия, улица, метро, телефон.
            }
            TestOrderPage.putTextToInput(locators.getInputLocateTextOfOrder()[4], TestOrderPage.getDataForOrderTest()[i][4]); // вставляем станцию
            TestOrderPage.waitMetroVisible(locators.getInputLocateTextOfOrder()[7]);
            TestOrderPage.clickElement(locators.getInputLocateTextOfOrder()[7]);

            TestOrderPage.clickElement(locators.getLocateButtonOnPageOrder()[1]);

            for (int j=5;j<7;j++)
            {
                TestOrderPage.putTextToInput(locators.getInputLocateTextOfOrder()[j], TestOrderPage.getDataForOrderTest()[i][j]);
                //заполнение когда привезти и комментарий
            }

            TestOrderPage.clickElement(locators.getInputLocateTextOfOrder()[9]); // заполнение срока аренды
            TestOrderPage.waitLoadingElement(locators.getInputLocateTextOfOrder()[12]);
            TestOrderPage.clickElement(locators.getInputLocateTextOfOrder()[12]);

            TestOrderPage.clickElement(locators.getInputLocateTextOfOrder()[10]);
            TestOrderPage.clickElement(locators.getInputLocateTextOfOrder()[11]);

            TestOrderPage.clickElement(locators.getLocateButtonOnPageOrder()[0]);//Кнопка заказать
            TestOrderPage.clickElement(locators.getLocateButtonOnPageOrder()[2]);//Кнопка Да

            chDriver.findElement(locators.getLocateTextOfOrdering()); // проверка, что появилась рамка успешнеого оформления
            chDriver.get(mainPageUrl);
        }
    }
    @Test
    public void checkEnabledButtonsOrder()
    {
        chDriver.findElement(locators.getLocatorsButtonMainPage()[0]).click();
        assertEquals(orderUrl, chDriver.getCurrentUrl());

        chDriver.navigate().back();

        chDriver.findElement(locators.getLocatorsButtonMainPage()[1]).click();
        assertEquals(orderUrl, chDriver.getCurrentUrl());
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
        TestMainPage.clickElement(locators.getLocatorCoockie());//кликаем на куки, чтобы оно не мешало

    }
    @After
    public void tearDown()
    {
        chDriver.quit();
    }
}
