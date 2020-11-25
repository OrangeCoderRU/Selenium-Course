package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.List;

public class ViewLogs extends TestBase{

    @Test
    public void ViewLogs() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> products = driver.findElements(By.cssSelector("table.dataTable tr"));

//        List <String> logs = new ArrayList<>();

        for (int i = 5; i < products.size(); i++){
            WebElement element = driver.findElement(By.cssSelector("table.dataTable tr:nth-child(" + i + ")"));
            String name_prod = element.getText();
            element.findElement(By.cssSelector("td:nth-child(3) > a")).click();
            List <LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() == 0){
                System.out.println("Для продукта - " + name_prod + " - логов не найдено");
            }
            else{
                System.out.println("Логи " + name_prod + ":");
                logs.forEach(l -> System.out.println(l));
            }
            driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
}
