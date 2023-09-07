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
        for (int i=0;i<locators.getLocatorsBodyQuestionAboutImportant().length;i++)
        {
            arrOfActualHeader[i] = TestMainPage.getTextOfElement(locators.getLocatorsHeaderQuestionAboutImportant()[i]);
            TestMainPage.clickElement(locators.getLocatorsHeaderQuestionAboutImportant()[i]);
            TestMainPage.waitLoadingElement(locators.getLocatorsBodyQuestionAboutImportant()[i]);
            arrOfActualBody[i] = TestMainPage.getTextOfElement(locators.getLocatorsBodyQuestionAboutImportant()[i]);

        }
        Assert.assertArrayEquals(arrOfActualHeader, TestMainPage.getExpectedHeaderTextsQuestionAboutImportant());
        Assert.assertArrayEquals(arrOfActualBody, TestMainPage.getExpectedBodyTextsQuestionAboutImportant());
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
