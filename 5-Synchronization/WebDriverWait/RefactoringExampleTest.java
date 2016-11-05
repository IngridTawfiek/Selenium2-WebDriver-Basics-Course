package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class RefactoringExampleTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setupForTest(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
        //Instantiate wait to reuse it
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void wrapAnonymousClassInMethod(){

        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value = '3']")).click();

        wait.until(optionWithValueDisplayed("23")).click();

        driver.findElement(By.name("submitbutton")).click();

        WebElement languageWeUSed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals(languageWeUSed.getText(), "23");

    }

    private ExpectedCondition <WebElement> optionWithValueDisplayed(final String value){
        return new ExpectedCondition<WebElement>(){
            @Override
                public WebElement apply(WebDriver webDriver){
                return webDriver.findElement(By.cssSelector("option[value ='" + value + "'"));
            }

            @Override
            public String toString(){
                return "option with value " + value + " displayed";
            }
        };
    }
}
