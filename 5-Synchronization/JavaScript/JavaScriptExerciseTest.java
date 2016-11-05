package com.seleniumsimplified.webdriver.Synchronization.JavaScript;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class JavaScriptExerciseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void SetUp(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/canvas_basic.html");
    }

    @Before
    public void resetBeforeTest(){
        driver.navigate().refresh();
    }

    @Test
    public void callTheDrawFunction(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int actionsCount = driver.findElements(By.cssSelector("#commandlist>li")).size();
        assertTrue(actionsCount == 2);
        for(int testLoop = 0; testLoop < 10; testLoop++) {
           js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);",
                   testLoop * 20, testLoop * 20,
                   "#" + testLoop + testLoop + "0000");
        }

        actionsCount = driver.findElements(By.cssSelector("#commandlist>li")).size();
        assertTrue(actionsCount == 12);
}

    @Test
    public void Add(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object add = js.executeScript("return(arguments[0] + arguments[1]);", 5,5);
        assertEquals(10L, add);
    }

    @Test
    public void changeTitle(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.getTitle().equals("Javascript Canvas Example"));
        js.executeScript("document.title = arguments[0]", "Bob");
        assertTrue(driver.getTitle().equals("Bob"));
    }

    @Test
    public void HideBody() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        final WebElement Body = driver.findElement(By.cssSelector("body"));
        assertTrue(Body.isDisplayed());
        js.executeScript("$(arguments[0]).hide();", Body);
        assertFalse(driver.findElement(By.id("commands")).isDisplayed());
//        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                if (Body.isDisplayed()) ;
//                return false;
//            }
//        });
    }

    @Test
    public void hideBody2(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertTrue(driver.findElement(By.id("commands")).isDisplayed());
        js.executeScript("$('body').hide();");
        assertFalse(driver.findElement(By.id("commands")).isDisplayed());
    }

    @Test
    public void JavascriptRunAsAnonymous(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //this code run as anonymous function without living trace
        js.executeScript("alert('alert triggered by webdriver')");
        assertTrue(driver.switchTo().alert().getText().equals("alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        //this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');};" +
                "window.webdriveralert.call();");

        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        //this can only work if we managed to keep javascript lying around
        js.executeScript("window.webdriveralert.call();");
        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();
    }

}

