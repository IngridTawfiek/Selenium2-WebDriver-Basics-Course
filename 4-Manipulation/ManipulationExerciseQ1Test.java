package com.seleniumsimplified.webdriver.Manipulation;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ManipulationExerciseQ1Test {
    static WebDriver driver;

    @Before
    public void initializeDriver(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
}

    @Test
    public void PAgeTitleChangeswithClick(){
        // name="submitbutton">
        driver.findElement(By.cssSelector("input[type=\"submit\"][name = 'submitbutton']")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertEquals(driver.getTitle(), "Processed Form Details");
    }

    @Test
    public void PAgeTitleChangeswithSubmit(){
        // name="submitbutton">
        driver.findElement(By.cssSelector("input[type=\"submit\"][name = 'submitbutton']")).submit();
        assertEquals(driver.getTitle(), "Processed Form Details");
    }

    //It's Input not Select

//    @Test
//    public void PAgeTitleChangeswithSubmitWithSelect(){
//        // name="submitbutton">
//        WebElement SubmitButton = driver.findElement(By.cssSelector("input[type=\"submit\"][name = 'submitbutton']"));
//        Select Submit = new Select(SubmitButton);
//        System.out.println(Submit.getOptions());
//    }

    @Test
    public void PAgeTitleChangeswithSubmitForm(){
        driver.findElement(By.cssSelector("form#HTMLFormElements")).submit();
        assertEquals(driver.getTitle(), "Processed Form Details");
    }

    @Test
    public void PAgeTitleChangeswithAnyFormElement(){
        driver.findElement(By.cssSelector("input[type='password']")).submit();
        assertEquals(driver.getTitle(), "Processed Form Details");
    }

    @Test
    public void SubmitFormWithKeyPresst(){
//        driver.findElement(By.cssSelector("input[type=\"submit\"][name = 'submitbutton']")).sendKeys(Keys.SPACE);
        driver.findElement(By.cssSelector("input[type=\"submit\"][name = 'submitbutton']")).sendKeys(Keys.ENTER);
        assertEquals(driver.getTitle(), "Processed Form Details");
    }
}

