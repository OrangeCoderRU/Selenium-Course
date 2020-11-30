package org.maxsa.pageobj.tests;

import org.junit.Before;
import org.maxsa.pageobj.app.Application;

public class TestBase {
    public Application app;

    @Before
    public void start(){
        app = new Application();
    }
}
