package com.seleniumsimplified.webdriver.Manipulation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ManipulationExerciseQ4Test {

    WebDriver driver;

    @Before
    public void startDriver(){
        driver = new FirefoxDriver();
        driver.get("http://compendiumdev.co.uk/selenium" + "/basic_html_form.html");
    }

    @Test
    public void checkbox1(){
        List <WebElement> Checkboxes = driver.findElements(By.cssSelector("[type='checkbox']"));
        for (WebElement e:Checkboxes ){
            if(e.isSelected()){
                e.click();
            }
        }
        driver.findElement(By.cssSelector("[type=\"checkbox\"][value='cb1']")).click();
        driver.findElement(By.cssSelector("[value = 'submit']")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        assertTrue(driver.findElements(By.cssSelector("[name = '_checkboxes'] + ul li")).size() == 1);
        WebElement CheckboxResult = driver.findElement(By.cssSelector("#_valuecheckboxes0"));
        assertThat(CheckboxResult.getText(), is("cb1"));
    }

    @After
    public void quitDriver(){
        driver.close();
    }
}
