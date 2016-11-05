package com.seleniumsimplified.webdriver.Interrogate.Driver;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DriverLevelInterrogationExerciseTest {

    private static WebDriver driver;
    final private static String PROTOCOL = "http";
    final private static String DOMAIN = "www.compendiumdev.co.uk";
    final private static String ROOT_URL = PROTOCOL + "://" + DOMAIN;

    @BeforeClass
    public static void createDriver(){
        driver = new FirefoxDriver();
        driver.get(ROOT_URL + "/selenium/basic_web_page.html");
    }

    @Test
    public void getTitleEx(){
        System.out.println(driver.getTitle());
        assertThat("Title should contains String Basic Web Page Title", "Basic Web Page Title", containsString(driver.getTitle()));
    }

    @Test
    public void getCurrentURLEx(){
        System.out.println(driver.getCurrentUrl());
        assertEquals("Current URL should be" + ROOT_URL + "/selenium/basic_web_page.html",
                "http://www.compendiumdev.co.uk/selenium/basic_web_page.html",
                driver.getCurrentUrl());
    }

    @Test
    public void getPageSource(){
        System.out.println(driver.getPageSource());
        assertTrue("Page Source contains “A paragraph of text”", driver.getPageSource().contains("A paragraph of text"));
    }

    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }

}
