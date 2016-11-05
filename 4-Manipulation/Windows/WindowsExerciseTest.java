package com.seleniumsimplified.webdriver.Manipulation.Windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WindowsExerciseTest {

    @Test
    public void WindowHandle(){
    Driver.quit();
    WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        String framesWindowHandle = driver.getWindowHandle();
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href = 'http://www.seleniumsimplified.com']")).click();
        String newWindowHandle = framesWindowHandle;
        Iterator<String> aHandle = driver.getWindowHandles().iterator();
        while (newWindowHandle.equals(framesWindowHandle)){
            newWindowHandle = aHandle.next();
        }
        driver.switchTo().window(newWindowHandle);
        new WebDriverWait (driver, 10L).until(ExpectedConditions.titleContains("Selenium Simplified"));
        driver.switchTo().window(framesWindowHandle);
        assertTrue(driver.getTitle().contains("Frameset Example Title"));
        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(framesWindowHandle);
    }

    @Test
    public void WindowSwitchByName(){
        Driver.quit();
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        String framesWindowHandle = driver.getWindowHandle();
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[id = 'goevil']")).click();
        driver.findElement(By.cssSelector("a[target = 'compdev']")).click();
        assertEquals(3, driver.getWindowHandles().size());
        driver.switchTo().window("evil");
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(3));
        assertThat("Windows switched to title is \"Home | EvilTester.com\"", driver.getTitle(),is("Home | EvilTester.com"));
        driver.switchTo().window("compdev");
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Software Testing Essays, Book Reviews and Information"));
        driver.switchTo().window(framesWindowHandle);
        assertEquals("Frameset Example Title (Example 6)", driver.getTitle());
        driver.switchTo().window("compdev");
        driver.close();
        assertTrue(driver.getWindowHandles().size()== 2);
        driver.switchTo().window("evil");
        driver.close();
        assertTrue(driver.getWindowHandles().size()== 1);
    }
}


