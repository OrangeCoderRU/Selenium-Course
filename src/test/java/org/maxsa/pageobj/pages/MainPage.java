package org.maxsa.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }
    public void goToBasket(){
        driver.findElement(By.cssSelector("div#cart")).click();

    }
}
