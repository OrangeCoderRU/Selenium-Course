package org.maxsa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CheckProduct extends TestBase {

    public List Parse_rgb(String rgb){

        List<String> list_rgb = new ArrayList<>(Arrays.asList(rgb.split(", ")));
        String test_string = list_rgb.get(0);
        StringBuilder new_test_string = new StringBuilder();

        for (int i = 0; i < test_string.length(); i++){
            Character alpha = test_string.charAt(i);
            if ((Character.isDigit(alpha)) && !(alpha.equals(")"))){
                new_test_string.append(alpha);
            }
        }

        list_rgb.set(0, new_test_string.toString());
        return list_rgb;

    }

    public double clear_px_char (String font){
        StringBuilder new_string = new StringBuilder();

        for (int i = 0; i < font.length(); i++){
            Character alpha = font.charAt(i);
            if ((Character.isLetter(alpha)))
            {

            }
            else{
                new_string.append(alpha);
            }
        }
        return Double.parseDouble(new_string.toString());
    }

    @Test
    public void CheckProduct(){

        WebElement main_product;
        WebElement page_product;
        String title_product_main;
        String title_product_page;
        String main_regular_price;
        String main_campaign_price;
        String page_regular_price;
        String page_campaign_price;
        String main_line_regprice;
        String main_color_regprice;
        String main_weight_campprice;
        String main_color_campprice;
        String page_color_regprice;
        String page_color_campprice;
        String font_regprice;
        String font_campprice;
        String page_font_regprice;
        String page_font_campprice;

        driver.get("http://localhost/litecart/en/");

        main_product = driver.findElement(By.cssSelector("#box-campaigns .name"));
        title_product_main = main_product.getAttribute("textContent");
        main_regular_price = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("textContent");
        main_campaign_price = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("textContent");
        main_line_regprice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("text-decoration-line");
        main_color_regprice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color");
        main_weight_campprice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-weight");
        main_color_campprice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color");
        font_regprice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("font-size");
        font_campprice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-size");


        //  обычная цена на главной зачёркнутая
        assertEquals("Обычная цена не зачеркнута", main_line_regprice, "line-through");

        // обычная цена на главной серая
        List gray_rgb = Parse_rgb(main_color_regprice);
        assertTrue(gray_rgb.get(0).equals(gray_rgb.get(1)) && gray_rgb.get(1).equals(gray_rgb.get(2)));

        // акционная цена на главной жирная (Chrome, FF возвращают 700, а Edge 900)
        boolean bold_price = Integer.parseInt(main_weight_campprice) >= 700;
        assertTrue(bold_price);

        // акционная цена на главной красная
        List red_rgb = Parse_rgb(main_color_campprice);
        assertTrue(red_rgb.get(1).equals("0") && red_rgb.get(2).equals("0"));

        // Edge rgba(204, 0, 0, 1)
        // Chrome rgba(204, 0, 0, 1)
        // FF rgb(204, 0, 0)

        // акционная цена больше обычной
        assertTrue(clear_px_char(font_campprice) > clear_px_char(font_regprice));

        main_product.click();

        page_product = driver.findElement(By.cssSelector("#box-product h1.title"));
        title_product_page = page_product.getAttribute("textContent");
        page_regular_price = driver.findElement(By.cssSelector(".price-wrapper .regular-price")).getAttribute("textContent");
        page_campaign_price = driver.findElement(By.cssSelector(".price-wrapper .campaign-price")).getAttribute("textContent");
        page_color_regprice = driver.findElement(By.cssSelector(".price-wrapper .regular-price")).getCssValue("color");
        page_color_campprice = driver.findElement(By.cssSelector(".price-wrapper .campaign-price")).getCssValue("color");
        page_font_regprice = driver.findElement(By.cssSelector(".price-wrapper .regular-price")).getCssValue("font");
        page_font_campprice = driver.findElement(By.cssSelector(".price-wrapper .campaign-price")).getCssValue("font");

        // Обычная цена на странице товара серая
        List page_gray_rgb = Parse_rgb(page_color_regprice);
        assertTrue(page_gray_rgb.get(0).equals(page_gray_rgb.get(1)) && page_gray_rgb.get(1).equals(page_gray_rgb.get(2)));

        // акционная цена на странице товара красная
        List page_red_rgb = Parse_rgb(page_color_campprice);
        assertTrue(page_red_rgb.get(1).equals("0") && page_red_rgb.get(2).equals("0"));

        // акционная цена на странице товара больше обычной
        assertTrue(clear_px_char(page_font_campprice) > clear_px_char(page_font_regprice));

        // Проверка текста заголовков и цен
        assertEquals("Заголовки отличаются", title_product_main, title_product_page);
        assertEquals("Обычные цены отличаются", main_regular_price, page_regular_price);
        assertEquals("Акционные цены отличаются", main_campaign_price, page_campaign_price);



    }
}
