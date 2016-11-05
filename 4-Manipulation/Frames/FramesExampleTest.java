package com.seleniumsimplified.webdriver.Manipulation.Frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FramesExampleTest {

    @Test
    public void frameEx(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        assertEquals("Frameset Example Title (Example 6)", driver.getTitle());
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href = 'frames_example_1.html']")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Frameset Example Title (Example 1)"));
        assertTrue("new title", driver.getTitle().equals("Frameset Example Title (Example 1)"));
    }
}
