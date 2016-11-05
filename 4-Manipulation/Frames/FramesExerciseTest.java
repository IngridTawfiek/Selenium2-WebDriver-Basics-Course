package com.seleniumsimplified.webdriver.Manipulation.Frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FramesExerciseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void refreshPage(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        wait = new WebDriverWait(driver, Driver.DEFAULT_TIMEOUT_SECONDS);
    }

    @Test
    public void ContentEx(){
        //Switch to Frame content
        driver.switchTo().frame(2);
        //check that tagname h1 contains "Content"
        WebElement Content = driver.findElement(By.cssSelector("h1"));
        assertEquals("H1 Content inside the frame 'Content'", "Content", Content.getText());
        //Click on "Load Green PAge" in frame "content"
        driver.findElement(By.cssSelector("[href='green.html']")).click();
        //Wait till Text "Back to original page" appears on the same form
        wait.until(presenceOfElementLocated(By.xpath("//a['Back to original page']")));
        //Check that h1 changed to "Green Page" in the same form
        Content = driver.findElement(By.cssSelector("h1"));
        assertFalse("h1 changed from 'Content' to Green Page", Content.getText().equals("Content"));
        System.out.println(Content.getText());
        //click on "Load Main frames page" on the same form
        driver.findElement(By.cssSelector("[href=\"content.html\"]")).click();
        wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
        //Check that tagname "h1" returned back to "Content"
        Content = driver.findElement(By.cssSelector("h1"));
        assertEquals("H1 Content inside the frame 'Content'", "Content", Content.getText());
        }

    @Test
    public void MenuEx(){
        //Switch to frame "menu"
        driver.switchTo().frame("menu");
        //check that the title haven't changed yet
        assertEquals("Title haven't changed yet", driver.getTitle(), "Frameset Example Title (Example 6)");
        //Click on the "iFrames Example" in the "menu" frame
        driver.findElement(By.xpath("//a[@href = 'iframe.html']")).click();
        //wait until title changes in the new frame
        wait.until(ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));
        assertTrue("Title have changed", driver.getTitle().equals("HTML Frames Example - iFrame Contents"));
        //Since frame has changed we have to switch to the new frame
        driver.switchTo().frame(0);
        //click on "Example 5"
        driver.findElement(By.cssSelector("[href = 'frames_example_5.html']")).click();
        //wait until title changes in the new frame
        wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 5)"));
        //switch again to frame "content"
        driver.switchTo().frame("content");
        //check that tagnames "a" in the body of this frame are 9
        assertThat("number of tagnames 'a' in this frame", driver.findElements(By.tagName("a")).size(),
                                                            is(9));
        //click on "Load Main frames page" on this form
        driver.findElement(By.cssSelector("a[href = 'index.html']")).click();
        //wait until page title changes
        wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
        //check no. of frames
        assertEquals("One frame added from the last page", 5, driver.findElements(By.xpath("//frame")).size());
    }
}
