package com.seleniumsimplified.webdriver.Manipulation.Alert;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AlertExerciseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void openDriver(){
        driver= Driver.get("http://compendiumdev.co.uk/selenium/alerts.html");
    }

    @Before
    public void refresh(){
        driver.navigate().refresh();
    }

    @Test
    public void alert(){
        WebElement alertButton;
        alertButton = driver.findElement(By.cssSelector("#alertexamples"));
        //click on the alert button
        alertButton.click();
        //Check AlertBox Title
        assertEquals("I am an alert box!", driver.switchTo().alert().getText());
        //dismiss the alert
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void confirm(){
        WebElement confirmButton;
        confirmButton = driver.findElement(By.cssSelector("#confirmexample"));
        //Check the Text under confirm Box
        WebElement confirmText = driver.findElement(By.cssSelector("#confirmreturn"));
        assertEquals(confirmText.getText(), "cret");
        //Click on Confirm Button
        confirmButton.click();
        //Check the Title on the Confirm Box alert
        assertEquals("I am a confirm alert", driver.switchTo().alert().getText());
        //Dismiss the confirm
        driver.switchTo().alert().dismiss();
        //check the Text under confirm button after dismiss
        confirmText = driver.findElement(By.cssSelector("#confirmreturn"));
        assertEquals(confirmText.getText(), "false");
        //Click again on confirm button
        confirmButton.click();
        //Accept the confirm
        driver.switchTo().alert().accept();
        //Check the Text below Confirm Button after accepting
        confirmText = driver.findElement(By.cssSelector("#confirmreturn"));
        assertEquals(confirmText.getText(), "true");
    }

    @Test
    public void prompt(){
        WebElement promptButton;
        promptButton = driver.findElement(By.cssSelector("#promptexample"));
        //Check the Text under prompt button
        WebElement promptText = driver.findElement(By.id("promptreturn"));
        assertEquals("pret", promptText.getText());
        //Click on prompt button
        promptButton.click();
        //Check the Title of the promprt alert box
        assertThat("Title of alert box", driver.switchTo().alert().getText(), is("I prompt you"));
        //Dismiss the prompt box without changing text in it
        driver.switchTo().alert().dismiss();
        promptText = driver.findElement(By.id("promptreturn"));
        //Check Text under prompt box after dismiss and not changing text
        assertTrue("Text after dismiss", promptText.getText().equals("pret"));
        //Click again on the promptbox
        promptButton.click();
        //Dismiss promptButton after changing text
        //Action ChangeText = new Actions(driver).sendKeys(Keys.chord(Keys.ARROW_RIGHT, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE)).build();
        //ChangeText.perform();
        driver.switchTo().alert().sendKeys("change");
        driver.switchTo().alert().dismiss();
        //Check the text under the prompt box after changing text and dismiss
        promptText = driver.findElement(By.cssSelector("#promptreturn"));
        assertFalse("Text won't change with dismiss", promptText.getText().equals("change"));
        //Click again on the prompt button
        promptButton.click();
        //accept the prompt box without changing Text in it
        driver.switchTo().alert().accept();
        promptText = driver.findElement(By.id("promptreturn"));
        //Check text under prompt box after accepting and not changing text
        assertTrue("Text changes to autoumatically to'change me'", promptText.getText().equals("change me"));
        //Click again on prompt button
        promptButton.click();
        //change text in prompt box then accept
        driver.switchTo().alert().sendKeys("change");
        driver.switchTo().alert().accept();
        //Check Text below prompt box after changing text and accepting
        promptText = driver.findElement(By.cssSelector("#promptreturn"));
        assertTrue("Text will change", promptText.getText().equals("change"));
    }
}
