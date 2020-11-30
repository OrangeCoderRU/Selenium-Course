package org.maxsa.pageobj.tests;

import org.junit.Test;
import org.maxsa.pageobj.pages.MainPage;
import org.maxsa.pageobj.pages.ProductPage;
public class BasketTest extends TestBase{

    public MainPage mainPage;
    public ProductPage productPage;

    @Test
    public void BasketTest() throws InterruptedException {
        app.open();
        for (int i = 0; i < 3; i++){
            app.addToBasket();
        }
        app.goToBasket();
        for (int i = 0; i < 3; i++){
            app.removeFromBasket();
        }

        app.quit();
    }
}
