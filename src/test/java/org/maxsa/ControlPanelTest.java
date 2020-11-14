package org.maxsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class ControlPanelTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent (final By locator){
        try{
            wait.until((WebDriver d) -> d.findElement(locator));
//            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    public boolean areElementsPresent (By locator){
        return driver.findElements(locator).size() > 0;
     }

    @Before
    public void start(){

        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void AdminPanelTest() {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menu = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app-"));

        for (int i = 1; i <= menu.size(); i++) {

//            isElementPresent(By.cssSelector("ul#box-apps-menu > li#app-"));
            WebElement elem = driver.findElement(By.cssSelector("ul#box-apps-menu > li#app-:nth-child(" + i + ")"));
            elem.click();

            if (areElementsPresent(By.cssSelector("li.selected ul.docs li"))) {
                List <WebElement> podmenu = driver.findElements(By.cssSelector("li.selected ul.docs li"));
                for (int a = 1; a <= podmenu.size(); a++)
                {
                    WebElement podelement = driver.findElement((By.cssSelector("li.selected ul.docs li:nth-child(" + a + ")")));
                    podelement.click();
                    driver.findElement(By.cssSelector("td#content > h1"));
                }
            }


        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
