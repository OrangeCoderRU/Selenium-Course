package org.maxsa;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
//        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
