package com.seleniumsimplified.webdriver.Manipulation.UserInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;
import sun.security.krb5.internal.ktab.KeyTabInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInteractionExerciseTest {

    WebDriver driver;

    @Before
    public void startDriver(){
        driver = new FirefoxDriver();
        driver.get("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");
    }

    @Test
    public void Q1Ex(){
        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));
        Action drag1Ondrop1 = new Actions(driver).clickAndHold(draggable1).moveToElement(droppable1).release().build();
        drag1Ondrop1.perform();
        assertTrue(droppable1.getText().equals("Dropped!"));
    }

    @Test
    public void Q2Ex(){
        WebElement draggable2 = driver.findElement(By.id("draggable2"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));
        Action drag1Ondrop1 = new Actions(driver).dragAndDrop(draggable2, droppable1).release().build();
        drag1Ondrop1.perform();
        assertTrue(droppable1.getText().equals("Get Off Me!"));
    }

    @Test
    public void Q3Ex(){
        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        Actions Press = new Actions(driver).keyDown(Keys.CONTROL).sendKeys("b").keyUp(Keys.CONTROL);
                Press.perform();
        assertTrue(draggable1.getText().equals("Bwa! Ha! Ha!"));
    }


    @Test
    public void Q4Ex(){
       WebElement canvas = driver.findElement(By.id("canvas"));
        WebElement EventList = driver.findElement(By.id("keyeventslist"));
        int eventCount = driver.findElements(By.tagName("li")).size();
        new Actions(driver).clickAndHold(canvas).moveByOffset(10,10).release().perform();
      assertTrue(eventCount < EventList.findElements(By.tagName("li")).size());
    }

    @Test
    public void Q5Ex() {
        WebElement red = driver.findElement(By.cssSelector("[class = 'droppable ui-droppable']"));
        Action first = new Actions(driver).click(red).build();
        first.perform();
        Action second = new Actions(driver).sendKeys(Keys.CONTROL, Keys.SPACE).build();
        second.perform();
//        assertEquals("Let GO!!!", red.getText());
//        try {
//            new Actions(driver).wait().perform();
//        catch (ExpectException)}
//
    }

    @Test
    public void Q6Ex(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement EventList = driver.findElement(By.id("keyeventslist"));
        js.executeScript("drawcanvas=true", "mycanvas2d.beginPath()", "mycanvas2d.rect(event.705 - canvasrect.697, event.183 - canvasrect.147, 2, 2)",
                "mycanvas2d.closePath()", "mycanvas2d.fill()");
        int eventCount = driver.findElements(By.tagName("li")).size();
//        assertTrue(eventCount < EventList.findElements(By.tagName("li")).size());
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
