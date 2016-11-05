package com.seleniumsimplified.webdriver.Manipulation.Windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowsExampleTest {

    @Test
    public void switchToNewWindow(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
        String framesWindowHandle = driver.getWindowHandle();
        assertEquals(1, driver.getWindowHandles().size());
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href = 'http://www.seleniumsimplified.com'")).click();
        assertEquals(2, driver.getWindowHandles().size());
        Set<String> MyWindows = driver.getWindowHandles();
        String newWindowHandle = "";
        for (String aHandle : MyWindows){
            if(!framesWindowHandle.contentEquals(aHandle)) {
                newWindowHandle = aHandle; break;
            }
            }
        driver.switchTo().window(newWindowHandle);
        assertTrue(driver.getTitle().contains("Selenium Simplified"));
        }
    }
