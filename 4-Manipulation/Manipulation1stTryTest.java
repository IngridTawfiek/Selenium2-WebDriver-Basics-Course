package com.seleniumsimplified.webdriver.Manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Manipulation1stTryTest {

    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        driver = new FirefoxDriver();
    }

    @Test
    public void Category(){
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        //find an element <option value="3">Server</option>
        driver.findElement(By.cssSelector("option[value = '3']")).click();

        // find an element <option value="23">Java</option>
        driver.findElement(By.cssSelector("option[value = '23']")).click();

        //find an element <input type="submit" value="Code In It" name="submitbutton"/>
        driver.findElement(By.cssSelector("input[name = 'submitbutton']")).click();

        //find an element <li id="_valuelanguage_id">23</li>
        assertThat(driver.findElement(By.cssSelector("li#_valuelanguage_id")).getText(), is("23"));
    }

    @Test
    public void Checkbox(){
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        WebElement checkBox1 = driver.findElement(By.cssSelector("[value=\"cb1\"]"));
        assertFalse(checkBox1.isSelected());
        checkBox1.click();
        assertTrue(checkBox1.isSelected());
    }
    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }
}
