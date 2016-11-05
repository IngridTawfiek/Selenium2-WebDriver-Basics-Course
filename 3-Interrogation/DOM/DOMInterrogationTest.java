package com.seleniumsimplified.webdriver.Interrogate.DOM;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertEquals;

public class DOMInterrogationTest {
    @Test
    public void WebElementInterrogationBasics(){
    final WebDriver driver;
    final String theTestPageUrlEqual = "http://www.compendiumdev.co.uk" + "/selenium/basic_web_page.html";
        driver = Driver.get();
        driver.navigate().to(theTestPageUrlEqual);
        WebElement para1 = driver.findElement(By.id("para1"));
        System.out.println(para1.getText());
        assertEquals("A paragraph of text", para1.getText());
}
}