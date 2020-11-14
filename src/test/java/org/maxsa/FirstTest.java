package org.maxsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
//        Разные браузеры

//        driver = new FirefoxDriver();

//        System.setProperty("webdriver.edge.driver","C:\\webdrivers\\MicrosoftWebDriver.exe.");
//        driver = new EdgeDriver();

//        Firefox по старой схеме (Selenium > 3.3)
//        FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//        driver = new FirefoxDriver(options);

//        Firefox Nightly
//        FirefoxOptions option = new FirefoxOptions();
//        option.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
//        driver = new FirefoxDriver(option);

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void FirstTest(){
        driver.get("http://www.yandex.ru/");
    }

    @Test
    public void LoginTest(){
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
