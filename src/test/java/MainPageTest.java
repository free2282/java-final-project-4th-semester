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
public class MainPageTest
{
    private WebDriver chDriver;
    private Locators locators;
    private MainPage TestMainPage;
    private OrderPage TestOrderPage;
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    private String orderUrl = "https://qa-scooter.praktikum-services.ru/order";



    @Test
    public void checkTextAboutImportant()
    //Первый тест кейс
    {
        String[] arrOfActualHeader = new String[8];
        String[] arrOfActualBody = new String[8];
        for (int i=0;i<locators.returnLocatorsBodyQuestionAboutImportant().length;i++)
        {
            arrOfActualHeader[i] = TestMainPage.returnTextOfElement(locators.returnLocatorsHeaderQuestionAboutImportant()[i]);
            TestMainPage.clicklement(locators.returnLocatorsHeaderQuestionAboutImportant()[i]);
            TestMainPage.waitLoadingElement(locators.returnLocatorsBodyQuestionAboutImportant()[i]);
            arrOfActualBody[i] = TestMainPage.returnTextOfElement(locators.returnLocatorsBodyQuestionAboutImportant()[i]);

        }
        Assert.assertArrayEquals(arrOfActualHeader, TestMainPage.getExpectedHeaderTextsQuestionAboutImportant());
        Assert.assertArrayEquals(arrOfActualBody, TestMainPage.getExpectedBodyTextsQuestionAboutImportant());
    }
    @Test
    public void checkEnabledButtonsOrder()
    {
        chDriver.findElement(locators.returnLocatorsButtonMainPage()[0]).click();
        assertEquals(orderUrl, chDriver.getCurrentUrl());

        chDriver.navigate().back();

        chDriver.findElement(locators.returnLocatorsButtonMainPage()[1]).click();
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
        TestMainPage.clicklement(locators.returnLocatorCoockie());//кликаем на куки, чтобы оно не мешало

    }
    @After
    public void tearDown()
    {
        chDriver.quit();
    }
}
