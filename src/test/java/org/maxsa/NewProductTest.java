package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class NewProductTest extends TestBase{

    public boolean isElementPresent(final By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
//            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            return false;
        }
    }

    @Test
    public void NewProductTest() throws InterruptedException {

        String name_product = "ElectroScooter";
//
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // Иногда click() не срабатывает из-за недогрузившейся страницы
        Thread.sleep(500);

        WebElement catalog = driver.findElement(By.cssSelector("ul#box-apps-menu #app-:nth-child(2)"));
        new Actions(driver).moveToElement(catalog).click().perform();

        WebElement add_product = driver.findElement(By.cssSelector("td#content > div > a:nth-child(2)"));
        new Actions(driver).moveToElement(add_product).click().perform();

        Thread.sleep(500);

        File picture_product = new File("pictures/scooter.jpg");
        String absolute = picture_product.getAbsolutePath();


        WebElement status = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input[@type='radio']"));
        new Actions(driver).click(status).perform();

        driver.findElement(By.cssSelector("input[name='name[en]'")).sendKeys(name_product);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("ES");


        driver.findElement(By.xpath("//strong[normalize-space()='Product Groups']/following-sibling::div/table/tbody/tr[last()]/td/input[@type='checkbox']")).click();

        WebElement quantity = driver.findElement(By.cssSelector("input[name='quantity']"));
        quantity.clear();
        quantity.sendKeys("5");

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(absolute);

        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("21-11-2020");

        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("21-12-2020");

        driver.findElement(By.cssSelector("div.tabs > ul > li:nth-child(2)")).click();

        driver.findElement(By.cssSelector("select[name='manufacturer_id'] > option[value='1']")).click();
        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("electro scooter transport ");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("Something short description");

        WebElement text_area_description = driver.findElement(By.cssSelector("span.input-wrapper div.trumbowyg-editor"));
        new Actions(driver).moveToElement(text_area_description).click().sendKeys("something description").perform();

        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("Something title");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("Something meta");

        driver.findElement(By.cssSelector("div.tabs > ul > li:nth-child(4)")).click();

        WebElement price_purchase = driver.findElement(By.cssSelector("input[name='purchase_price']"));
        price_purchase.clear();
        price_purchase.sendKeys("300");

        driver.findElement(By.cssSelector("select[name='purchase_price_currency_code'] > option[value='USD']")).click();
        WebElement price = driver.findElement(By.cssSelector("input[name='gross_prices[USD]"));
        price.clear();
        price.sendKeys("350");

        driver.findElement(By.cssSelector("button[name='save'")).click();

        assertTrue(isElementPresent(By.xpath("//a[normalize-space()='" + name_product + "']")));


    }
}
