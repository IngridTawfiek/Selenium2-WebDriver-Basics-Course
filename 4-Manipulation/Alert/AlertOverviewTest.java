package com.seleniumsimplified.webdriver.Manipulation.Alert;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class AlertOverviewTest {

   public static WebDriver driver;

    @BeforeClass
    public static void openDriver(){
      driver= Driver.get("http://compendiumdev.co.uk/selenium/alerts.html");
    }

    @Before
    public void refresh(){
        driver.navigate().refresh();
    }

    @Test
    public void try1(){
        WebElement alertButton;
        alertButton = driver.findElement(By.cssSelector("#alertexamples"));
        alertButton.click();
        assertEquals("I am an alert box!", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
}
