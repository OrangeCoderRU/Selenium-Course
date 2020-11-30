package org.maxsa.pageobj.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Page{

    public ProductPage(WebDriver driver){
        super(driver);

    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
    
    public void openProduct(){
        driver.findElement(By.cssSelector("#box-most-popular .name")).click();
    }
    public void addToBasket(){
        if (areElementsPresent(By.cssSelector("select[name='options[Size]']"))){
            driver.findElement(By.cssSelector("select[name='options[Size]'] > option[value='Small'] ")).click();
        }
        WebElement basket = driver.findElement(By.cssSelector("span.quantity"));
        String current_basket = basket.getText();
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), current_basket)));
    }
}
