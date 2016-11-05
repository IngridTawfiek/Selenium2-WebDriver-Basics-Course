package com.seleniumsimplified.webdriver.Browsers;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DriverManagerTest {

    WebDriver driver;

    @Test
    public void createFirefoxDriver(){
        System.setProperty(DriverManager.SELENIUM2_BASICS_DRIVER,"firefox");
        assertBrowserTestRun();

    }

    @Test
    public void createHtmlUnitDriver() {
        System.setProperty(DriverManager.SELENIUM2_BASICS_DRIVER,"htmlunit");
        assertBrowserTestRun();
    }

    @Test
    public void createDefaultDriver() {
        assertBrowserTestRun();
    }
//
//    @Test
//    public void createOperaDriver() {
//        System.setProperty(DriverManager.SELENIUM2_BASICS_DRIVER,"opera");
//        assertBrowserTestRun();
//    }

    public void assertBrowserTestRun(){

        driver = DriverManager.get();
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
    }

    @After
    public void QuitDriver(){
        driver.quit();
    }
}
