package com.seleniumsimplified.webdriver.Manipulation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ManipulationExerciseQ5Test {

    public WebDriver driver;

    @Before
    public void startDriver(){
        driver = new FirefoxDriver();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void dropDownTest(){
        WebElement dropDown = driver.findElement(By.cssSelector("[value = 'dd5']"));
        dropDown.click();
        clickSubmitButton();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertDropDownResultIsCorrect();
    }

    @Test
    public void dropDownTestByKeyBoard(){
        WebElement dropDown = driver.findElement(By.cssSelector("[name=\"dropdown\"]"));
        dropDown.sendKeys("dd");
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeSelected
                (By.cssSelector("[value = 'dd5']")));
        clickSubmitButton();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertDropDownResultIsCorrect();
    }

    @Test
    public void dropDownTestByKeyBoardSpecialKeys(){
        WebElement dropDown = driver.findElement(By.cssSelector("[name=\"dropdown\"]"));
        dropDown.sendKeys(Keys.chord(Keys.HOME, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN));
        clickSubmitButton();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertDropDownResultIsCorrect();
    }

    @After
    public void quitDriver(){
        driver.close();
    }

    private void clickSubmitButton(){
        WebElement SubmitButton = driver.findElement(By.cssSelector("[value = 'submit']"));
        SubmitButton.click();
    }

    private void assertDropDownResultIsCorrect(){
        WebElement dropDownResult = driver.findElement(By.cssSelector("#_valuedropdown"));
        assertEquals(dropDownResult.getText(), "dd5");
    }
}
