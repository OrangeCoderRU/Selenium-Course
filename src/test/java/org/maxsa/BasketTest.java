package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketTest extends TestBase {

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Test
    public void BasketTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");

        for (int i = 1; i <= 3; i++){

            WebElement product = driver.findElement(By.cssSelector("#box-most-popular .name"));
            product.click();

            if (areElementsPresent(By.cssSelector("select[name='options[Size]']"))){
                driver.findElement(By.cssSelector("select[name='options[Size]'] > option[value='Small'] ")).click();
            }

            WebElement basket = driver.findElement(By.cssSelector("span.quantity"));
            String current_basket = basket.getText();
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();

            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), current_basket)));

            driver.get("http://localhost/litecart/en/");
        }

        driver.findElement(By.cssSelector("div#cart")).click();

        for (int i = 1; i <= 3; i++){
            WebElement remove = driver.findElement(By.cssSelector("button[value='Remove']"));
            WebElement basket = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tr.footer td:nth-child(2)"));
            Thread.sleep(500);
            remove.click();

            wait.until(ExpectedConditions.stalenessOf(basket));

        }
        Thread.sleep(5000);
    }
}
