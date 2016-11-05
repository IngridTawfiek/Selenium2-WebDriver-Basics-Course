package com.seleniumsimplified.webdriver.Manipulation;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ManipulationExerciseQ2Test {
   public WebDriver driver;

    @Before
    public void OpenServer(){
        driver = new FirefoxDriver();
        driver.get("http://compendiumdev.co.uk/selenium" + "/basic_html_form.html");
    }

    @Test
    public void DefaultComments(){
        WebElement Text = driver.findElement(By.cssSelector("textarea[name=\"comments\"]"));
        Text.submit();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
        WebElement Text2 = driver.findElement(By.cssSelector("#_valuecomments"));
        assertThat(Text2.getText(), is("Comments..."));
    }

    @Test
    public void Comments(){
        WebElement Text = driver.findElement(By.cssSelector("textarea[name=\"comments\"]"));
        Text.clear();
        System.out.println(Text.getAttribute("value"));
        Text.sendKeys("Ingrid Tawfiek");
        //It's TextArea not Select
//        Select Textselected = new Select(Text);
//        System.out.println(Textselected.getOptions());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        WebElement Text2 = driver.findElement(By.cssSelector("#_valuecomments"));
        assertThat(Text2.getText(), is("Ingrid Tawfiek"));
    }


    @After
    public void QuitServer(){
        driver.quit();
    }
}
