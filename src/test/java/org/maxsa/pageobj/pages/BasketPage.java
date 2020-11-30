package org.maxsa.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPage extends Page {

    public BasketPage(WebDriver driver){
        super(driver);
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void removeProduct() throws InterruptedException {
        if (areElementsPresent(By.cssSelector("button[value='Remove']")))
        {
            WebElement remove = driver.findElement(By.cssSelector("button[value='Remove']"));
            wait.until(ExpectedConditions.visibilityOf(remove));
            WebElement basket = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tr.footer td:nth-child(2)"));
            Thread.sleep(500);
            remove.click();

            wait.until(ExpectedConditions.stalenessOf(basket));
        }

    }
}
