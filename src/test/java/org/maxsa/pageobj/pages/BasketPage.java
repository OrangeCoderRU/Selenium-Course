package org.maxsa.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver){
        super(driver);
    }

    public void removeProducts() throws InterruptedException {

        List<WebElement> basket_table = driver.findElements(By.cssSelector("#order_confirmation-wrapper tbody > tr:not(.header):not(.footer)"));
        // Останавливаем "карусель"
        driver.findElement(By.cssSelector("#checkout-cart-wrapper ul.shortcuts > li.shortcut")).click();

        // Кол-во товаров в корзине как размер List-а минус 3 нижних поля с итогом
        for (int i = 1; i <= (basket_table.size() - 3); i++ ) {

            WebElement remove = driver.findElement(By.cssSelector("button[value='Remove']"));
            WebElement basket = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tr.footer td:nth-child(2)"));
            Thread.sleep(500);
            remove.click();

            wait.until(ExpectedConditions.stalenessOf(basket));
        }
    }
}


