package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NewUserTest extends TestBase {

    public String city;
    public String post_code;
    public String tel;
    public String password;
    public String first_name;
    public String last_name;
    public String address;
    public String email;


    public String email_generate(){
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder e_mail = new StringBuilder();
        for (int i = 0; i <= 5; i++){
            e_mail.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return e_mail.toString() + "@mail.ru";
    }

    @Test
    public void NewUserTest(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        city = "Montgomery";
        post_code = "36101";
        tel = "+10000000000";
        password = "password";
        first_name = "test";
        last_name = "user";
        address = "something address";
        email = email_generate();

        driver.get("http://localhost/litecart/en/");
        driver.get(driver.findElement(By.cssSelector("[name='login_form'] tr:nth-child(5) a")).getAttribute("href"));


        // Регистрируемся
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(first_name);
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(last_name);
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys(address);
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys(post_code);
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys(city);
        driver.findElement(By.cssSelector("select > option[value='US']")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys(tel);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
        driver.findElement((By.cssSelector("button[name='create_account'"))).click();

//        driver.findElement(By.cssSelector("#box-account ul.list-vertical > a:not(li)")).click();

        driver.get("http://localhost/litecart/en/logout");

        // Входим в созданный аккаунт
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        driver.get("http://localhost/litecart/en/logout");
    }
}
