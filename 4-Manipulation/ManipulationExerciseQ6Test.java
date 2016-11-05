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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManipulationExerciseQ6Test {

    public WebDriver driver;

    @Before
    public void startDriver(){
        driver = new FirefoxDriver();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void multipleSelectTest(){
        WebElement multiSelect = driver.findElement(By.cssSelector("[multiple=\"multiple\"]"));
        List <WebElement> multiSelectOPtions = multiSelect.findElements(By.tagName("option"));
        multiSelectOPtions.get(0).click();
        multiSelectOPtions.get(1).click();
        multiSelectOPtions.get(2).click();
        if (multiSelectOPtions.get(3).isSelected()){
            multiSelectOPtions.get(3).click();
        }
        clickSubmitButton();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertmultipleSelectResultIsCorrect();
    }


    @Test
    public void multipleSelectTestwithSelectClass() {

        WebElement multiSelect = driver.findElement(By.cssSelector("[multiple=\"multiple\"]"));
        Select multiSelectOPtions = new Select(multiSelect);
        assertTrue(multiSelectOPtions.isMultiple());
        List<WebElement> multiSelectedElements = multiSelectOPtions.getAllSelectedOptions();
        assertEquals(multiSelectedElements.size(), 1);
        System.out.println(multiSelectedElements.get(0).getText());
        multiSelectOPtions.deselectAll();
        multiSelectedElements = multiSelectOPtions.getAllSelectedOptions();
        assertEquals(multiSelectedElements.size(), 0);
        multiSelectOPtions.selectByVisibleText("Selection Item 1");
        multiSelectOPtions.selectByIndex(1); //2nd
        multiSelectOPtions.selectByValue("ms3"); //3rd option
        multiSelectedElements = multiSelectOPtions.getAllSelectedOptions();
        assertEquals(multiSelectedElements.size(), 3);

        clickSubmitButton();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
        assertmultipleSelectResultIsCorrect();
    }

    @After
    public void quitDriver(){
        driver.close();
    }

    private void clickSubmitButton(){
        WebElement SubmitButton = driver.findElement(By.cssSelector("[value = 'submit']"));
        SubmitButton.click();
    }

    private void assertmultipleSelectResultIsCorrect(){
        assertEquals(driver.findElement(By.id("_valuemultipleselect0")).getText(), "ms1");
        assertEquals(driver.findElement(By.id("_valuemultipleselect1")).getText(), "ms2");
        assertEquals(driver.findElement(By.id("_valuemultipleselect2")).getText(), "ms3");
        assertTrue(driver.findElements(By.id("_valuemultipleselect3")).isEmpty());
    }
}
