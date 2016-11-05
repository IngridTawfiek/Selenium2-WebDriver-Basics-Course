package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements.XPath;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class XPathTest {

    public static WebDriver driver;

    @BeforeClass
    public static void InitiatindDriver(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void findSpecificPara(){
        WebElement element;
        element = driver.findElement(By.xpath("//p[@name = 'pName5']"));
        assertEquals("p5", element.getAttribute("id"));
    }

    @Test
    public void AssertNumberParagraph(){
        List <WebElement> elements;
        elements = driver.findElements(By.xpath("//p"));
        int nestedCount = 0;
        for (WebElement e : elements){
            if(e.getText().contains("nested para")){
                nestedCount++;
            }
        }
        assertEquals(41, elements.size());
        assertEquals(16, nestedCount);
    }

    @Test
    public void pathNavigation(){
        WebElement element = driver.findElement(By.xpath("//div[@id='div18']//a[@name='aName26']"));
        assertEquals("Expected matching id", "a26", element.getAttribute("id"));
    }

}
