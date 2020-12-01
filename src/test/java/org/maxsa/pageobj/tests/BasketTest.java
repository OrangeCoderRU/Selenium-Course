package org.maxsa.pageobj.tests;

import org.junit.Test;
public class BasketTest extends TestBase{


    @Test
    public void BasketTest() throws InterruptedException {
        app.open();
        for (int i = 0; i < 3; i++){
            app.addToBasket();
        }
        app.goToBasket();
        app.removeAllFromBasket();
        app.quit();
    }
}
