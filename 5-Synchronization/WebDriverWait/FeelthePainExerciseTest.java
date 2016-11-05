package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FeelthePainExerciseTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Goto(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void Test(){
        driver.findElement(By.cssSelector("#combo1>option[value = '3']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#combo2>option[value = '23']")));
        driver.findElement(By.cssSelector("#combo2>option[value = '23']")).click();
        driver.findElement(By.cssSelector("input[value = 'Code In It']")).click();
        wait.until(presenceOfElementLocated(By.id("_valuelanguage_id")));
        assertTrue(driver.findElement(By.id("_valuelanguage_id")).getText().equals("23"));
    }}
