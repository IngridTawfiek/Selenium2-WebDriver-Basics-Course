package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements.XPath;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertTrue;

public class XpathExerciseTest {
    public static WebDriver driver;

    @BeforeClass
    public static void initiateDriver(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void ByIdCssSelector(){
//        WebElement element = driver.findElement(By.id("p31"));
        WebElement element = driver.findElement(By.xpath("//*[@id='p31']"));
        assertTrue(element.getAttribute("name").contains("pName31"));
    }

    @Test
    public void BynameCssSelector(){
//        WebElement element = driver.findElement(By.name("ulName1"));
        WebElement element = driver.findElement(By.xpath("//*[@name= 'ulName1']"));
        assertTrue(element.getAttribute("id").contains("ul1"));
    }

    @Test
    public void ByclassNameCssSelector(){
//        WebElement element = driver.findElement(By.className("specialDiv"));
        WebElement element = driver.findElement(By.xpath("//*[@class='specialDiv']"));
        assertTrue(element.getAttribute("id").contains("div1"));
    }

    @Test
    public void BytagNameCssSelector(){
//        WebElement element = driver.findElement(By.tagName("li"));
        WebElement element = driver.findElement(By.xpath("//li"));
        assertTrue(element.getAttribute("name").contains("liName1"));
    }
}
