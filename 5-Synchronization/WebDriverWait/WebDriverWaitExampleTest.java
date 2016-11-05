package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class WebDriverWaitExampleTest {

    WebDriver driver = new FirefoxDriver();
    @Before
    public void startDriver(){
        driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_html_form.html");
    }

    @Test
    public void ExampleUsingExpectedCondition(){

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    @Test
    public void ExampleWithSleepTime() {

        new WebDriverWait(driver, 10, 50).until(ExpectedConditions.titleIs("HTML Form Elements"));
        assertEquals("HTML Form Elements", driver.getTitle());
    }
    @After
    public void quitDriver(){
        driver.quit();
    }
}
