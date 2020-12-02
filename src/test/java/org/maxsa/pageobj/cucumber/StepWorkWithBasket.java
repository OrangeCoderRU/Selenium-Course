package org.maxsa.pageobj.cucumber;

import io.cucumber.java8.En;
import org.maxsa.pageobj.app.Application;

public class StepWorkWithBasket implements En {
    private static Application app = new Application();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit(); app = null;}));
    }
    public StepWorkWithBasket() {
        When("we start application", () -> {
            app.open();
        });
        Then("we add {string} random items to the basket", (String arg0) -> {
            for (int i = 0; i < Integer.parseInt(arg0); i++){
                app.addToBasket();
            }
        });
        And("go to basket page", () -> {
            app.goToBasket();
        });
        Then("we remove all items from the basket", () -> {
            app.removeAllFromBasket();
        });
    }

}
