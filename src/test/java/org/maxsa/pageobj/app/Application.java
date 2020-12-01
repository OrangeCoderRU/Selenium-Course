package org.maxsa.pageobj.app;
import org.maxsa.pageobj.pages.BasketPage;
import org.maxsa.pageobj.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.maxsa.pageobj.pages.MainPage;
import org.maxsa.pageobj.pages.BasketPage;

public class Application {
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void open(){
        mainPage.open();
    }

    public void addToBasket() {
        mainPage.open();
        productPage.openProduct();
        productPage.addToBasket();
    }

    public void goToBasket(){
        mainPage.goToBasket();
    }
    public void removeAllFromBasket() throws InterruptedException {
        basketPage.removeProducts();
    }


}
