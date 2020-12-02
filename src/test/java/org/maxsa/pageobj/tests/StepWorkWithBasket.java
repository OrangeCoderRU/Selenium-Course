package org.maxsa.pageobj.tests;

import io.cucumber.java8.En;
import org.maxsa.pageobj.app.Application;

public class StepWorkWithBasket implements En {
    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit(); app = null;}));
    }
    public StepWorkWithBasket() {
        When("start application", () -> {
            app.open();
        });
        Then("we add {string} items in the basket", (String arg0) -> {
            for (int i = 0; i < Integer.parseInt(arg0); i++){
                app.addToBasket();
            }
        });
        And("go to basket", () -> {
            app.goToBasket();
        });
        Then("we remove all items from the basket", () -> {
            app.removeAllFromBasket();
        });
    }

}
