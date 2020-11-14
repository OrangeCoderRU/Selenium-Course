package org.maxsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class StickerTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void StickerTest(){
        driver.get("http://localhost/litecart/");
        List <WebElement> products = driver.findElements(By.cssSelector(".product"));

        for (WebElement duck : products){
            assertTrue((duck.findElements(By.cssSelector(".sticker"))).size() == 1);
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}