package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.util.List;

public class SwitchToHandle extends TestBase{

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Test
    public void SwitchToHandle() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.cssSelector("a.button")).click();

        String original_handle = driver.getWindowHandle();

        List<WebElement> inputs = driver.findElements(By.cssSelector("form tr"));

        inputs.get(1).findElement(By.cssSelector("a")).click();

        Thread.sleep(5000);
    }
}
