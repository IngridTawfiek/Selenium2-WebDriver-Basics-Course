package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements.CssSelectors;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertEquals;

public class CssSelectorsPathTest {

   public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        driver = new FirefoxDriver();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void DirectDescendant(){
        assertEquals("div > p", 41, driver.findElements(By.cssSelector("div > p")).size());
        assertEquals("div > li", 0, driver.findElements(By.cssSelector("div > li")).size());
    }

    @Test
    public void anyDescendant(){
        assertEquals("div pi", 41, driver.findElements(By.cssSelector("div p")).size());
        assertEquals("div li", 25, driver.findElements(By.cssSelector("div li")).size());
    }

    @Test
    public void SiblingsOfPreceding(){
        assertEquals("li + li", 24, driver.findElements(By.cssSelector("li + li")).size());
        assertEquals("li", 25, driver.findElements(By.cssSelector("li")).size());
    }

    @Test
    public void FirstChild(){
        assertEquals("li:first-child", 1, driver.findElements(By.cssSelector("li:first-child")).size());
    }

    @AfterClass
    public static void quitDriver(){

        driver.quit();
    }
}
