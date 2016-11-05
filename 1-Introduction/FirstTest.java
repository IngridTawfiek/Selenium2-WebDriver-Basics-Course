package com.seleniumsimplified.webdriver.Introduction;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.jnlp.DownloadService;

import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class FirstTest {

    private int value1, value2;

    @Test
    public void driverIsTheKing() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://compendiumdev.co.uk/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
    }

    @Before
    public void setup(){
        value1=3;
        value2=5;
    }

    @Test
    public void testAdd(){
        double result = value1 + value2;
        assertTrue(result==8);
        System.out.println(result);
    }
   }