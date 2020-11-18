package org.maxsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class ControlPanelTest extends TestBase {


    public boolean isElementPresent(final By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
//            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
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
                List<WebElement> podmenu = driver.findElements(By.cssSelector("li.selected ul.docs li"));
                for (int a = 1; a <= podmenu.size(); a++) {
                    WebElement podelement = driver.findElement((By.cssSelector("li.selected ul.docs li:nth-child(" + a + ")")));
                    podelement.click();
                    driver.findElement(By.cssSelector("td#content > h1"));
                }
            }


        }
    }

    public void check_alphabet(List<String> words) {
        List<String> words_sort = new ArrayList<>(words);

        Collections.sort(words_sort);

        assertEquals("Список не в алфавитном порядке", words_sort, words);
    }

    @Test
    public void CountryTest() {
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement country_form = driver.findElement(By.cssSelector("form[name='countries_form'"));
        List<WebElement> countries_web = country_form.findElements(By.cssSelector("tr.row"));
        List<String> true_timezones = new ArrayList<>();
        List<String> countries = new ArrayList<>();


        for (WebElement elem : countries_web) {
            // Проходим по странам и добавляем в список их названия

            WebElement current_country = elem.findElement(By.cssSelector("td:nth-child(5)"));
            countries.add(current_country.getAttribute("textContent"));
            int num_zones = Integer.parseInt(elem.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent"));

            if (num_zones != 0) {
                // Если таймзон больше 0 - копируем ссылку на страну
                WebElement link_true_country = current_country.findElement(By.cssSelector("a"));
                true_timezones.add(link_true_country.getAttribute("href"));
            }

        }

        // Мой метод проверки на алфавитность
        check_alphabet(countries);

        for (String link_country : true_timezones) {
            // Переходим по полученным ссылкам и работаем с таймзонами
            driver.get(link_country);

            List<String> timezone_names = new ArrayList<>();
            List<WebElement> timezones = driver.findElements(By.cssSelector("#table-zones tr"));
            for (WebElement row_timezone : timezones) {
                if (timezones.indexOf(row_timezone) == 0) {
                    continue;
                } else if (timezones.indexOf(row_timezone) == timezones.size() - 1) {
                    break;
                }
                WebElement name_country = row_timezone.findElement(By.cssSelector("td:nth-child(3)"));
                timezone_names.add(name_country.getAttribute("textContent"));
                check_alphabet(timezone_names);

            }

        }

    }

    @Test
    public void geoTest(){
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List <WebElement> list_countries = driver.findElements(By.cssSelector(".dataTable tr.row"));

        List <String> geozones_links = new ArrayList<>();
        List <String> code_geozones = new ArrayList<>();

        for (WebElement elem : list_countries) {
            // Копируем ссылки на страны
            WebElement row_link = elem.findElement(By.cssSelector("td:nth-child(3) > a"));
            geozones_links.add(row_link.getAttribute("href"));
        }

        for (String link_zone : geozones_links) {
            // Проходим по странам и проверяем их зоны
            driver.get(link_zone);
            List <WebElement> row_zones = driver.findElements(By.cssSelector("#table-zones tr:not(.header)"));
            for (WebElement zone : row_zones) {
                if (row_zones.indexOf(zone) == row_zones.size() - 1){
                    break;
                }
                code_geozones.add(zone.findElement(By.cssSelector("td:nth-child(3) option")).getAttribute("text"));
            }

            check_alphabet(code_geozones);
            code_geozones.clear();
        }

    }
}


