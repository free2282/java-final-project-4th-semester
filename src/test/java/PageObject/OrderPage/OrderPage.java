package PageObject.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage
{
    private WebDriver chDriver;

    public OrderPage(WebDriver chDriver)
    {
        this.chDriver = chDriver;
    }

    private String[][] dataForOrderTest =
            {
            {
                    "Иван",
                    "Сидоров",
                    "Улица Есенина, д.24, кв 75",
                    "+79295553099",
                    "Красносельская",
                    "30.08.2023",
                    "Позвоните на домофон",
            },
            {
                    "Тимофей",
                    "Шибаев",
                    "Улица Пушкина, д.24, кв 75",
                    "+79209411897",
                    "Красносельская",
                    "30.08.2023",
                    "Позвоните на домофон"
            }
            };
    private String[] firstTestOrderData =
            {
                    "Иван",
                    "Сидоров",
                    "Улица Есенина, д.24, кв 75",
                    "+79295553099",
                    "Красносельская",
                    "30.08.2023",
                    "Позвоните на домофон",
            };
    private String [] secondTestOrderData =
            {
                    "Тимофей",
                    "Шибаев",
                    "Улица Пушкина, д.24, кв 75",
                    "+79209411897",
                    "Красносельская",
                    "30.08.2023",
                    "Позвоните на домофон",
            };

    public String[][] getDataForOrderTest()
    {
        return dataForOrderTest;
    }

    public void waitLoadingElement(By element)
    {
        new WebDriverWait(chDriver, 4).until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void putTextToInput(By element, String text)
    {
         chDriver.findElement(element).sendKeys(text);
    }
    public void clickElement(By element)
    {
        chDriver.findElement(element).click();
    }
    public void waitMetroVisible(By element)
    {
        new WebDriverWait(chDriver, 3).until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
