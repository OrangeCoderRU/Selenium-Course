package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Time;
import java.util.List;
import java.util.Set;

public class SwitchToHandle extends TestBase {

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
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

        String original_window = driver.getWindowHandle();
        Set <String> existingWindows = driver.getWindowHandles();

        List<WebElement> inputs = driver.findElements(By.cssSelector("form tr"));

        for (WebElement link : inputs){
            int current_child = inputs.indexOf(link) + 1;
            if (areElementsPresent(By.cssSelector("form tr:nth-child(" + current_child + ") a"))){
                link.findElement(By.cssSelector("a:not(#address-format-hint)")).click();
                String newWindow = wait.until(anyWindowOtherThan(existingWindows));
                driver.switchTo().window(newWindow);
                driver.close();
                driver.switchTo().window(original_window);
            }
        }
    }
}
