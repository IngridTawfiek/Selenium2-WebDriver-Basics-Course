package com.seleniumsimplified.webdriver.Manipulation.UserInteractions;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class UserInteractonsIntroTest {
    WebDriver driver;

    @Before
    public void start(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
    }

    @Test
    public void myStuff(){
//        new Actions(driver).click(driver.findElement(By.cssSelector("input[value='cb1']"))).perform();
        Action cb1Click = new Actions(driver).click(driver.findElement(By.cssSelector("input[value='cb1']"))).build();
        cb1Click.perform();
        cb1Click.perform();
        cb1Click.perform();

    }
}
