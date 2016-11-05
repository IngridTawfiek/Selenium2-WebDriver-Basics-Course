package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait.CustomExpectedConditions;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CustomExpectedConditionsExampleTest {

    public WebDriver driver;

    @Before
    public void Setup(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void SyncronizationUsingSeparateNamedClassExpectedCondition(){

        //SelectServer
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value = '3']")).click();

        new WebDriverWait (driver, 10).until(new SelectContainsText(By.id("combo2"), "Java"));

        //Then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value = '23']")).click();

        //Submit the form
        WebElement CodeInIT = driver.findElement(By.name("submitbutton"));
        CodeInIT.click();

        new WebDriverWait(driver, 10).until(titleIs("Processed Form Details"));
        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java Code", "23", languageWeUsed.getText());
    }

    private class SelectContainsText implements ExpectedCondition<Boolean> {
        private String textToFind;
        private By FindBy;

        public SelectContainsText(final By comboFindBy, final String textToFind) {
            this.FindBy = comboFindBy;
            this.textToFind = textToFind;
        }

        @Override
        public Boolean apply(WebDriver webDriver) {
            WebElement combo = webDriver.findElement(this.FindBy);
            List<WebElement> options = combo.findElements(By.tagName("option"));
            for (WebElement anoption : options) {
                if (anoption.getText().equals(this.textToFind))
                    return true;
            }
            return false;
        }


        @Override
        public String toString(){
            return "select" + this.FindBy + " to contain " + this.textToFind;
        }
    }


    @Test
    public void SyncronizationUsingAnonymousClassExpectedCondition(){

        //SelectServer
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value = '3']")).click();

        new WebDriverWait (driver, 10).until(new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver webDriver){
                return webDriver.findElement(By.cssSelector("option[value = '23']")).isDisplayed();
            }
        }
        );

        //Then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value = '23']")).click();

        //Submit the form
        WebElement CodeInIT = driver.findElement(By.name("submitbutton"));
        CodeInIT.click();

        new WebDriverWait(driver, 10).until(titleIs("Processed Form Details"));
        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java Code", "23", languageWeUsed.getText());
    }

    @Test
    public void SyncronizationUsingAnonymousClassWebElementExpectedCondition(){

        //SelectServer
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value = '3']")).click();

        WebElement elly = new WebDriverWait (driver, 10).until(new ExpectedCondition<WebElement>() {
                                                                   @Override
                                                                   public WebElement apply(WebDriver webDriver) {
                                                                       WebElement elli = webDriver.findElement(By.cssSelector("option[value = '23']"));
                                                                       if(elli.isDisplayed())
                                                                           return elli;
                                                                       return null;
                                                                   }
                                                               }
        );

        //Then select Java

        elly.click();
        assertEquals("Expected Java", "Java", elly.getText());

        //Submit the form
        WebElement CodeInIT = driver.findElement(By.name("submitbutton"));
        CodeInIT.click();

        new WebDriverWait(driver, 10).until(titleIs("Processed Form Details"));
        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java Code", "23", languageWeUsed.getText());
    }

    @Test
    public void SyncronizationUsingWrapMethodExpectedCondition(){

        //SelectServer
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value = '3']")).click();

       WebElement javaOption = new WebDriverWait (driver, 10).until(OptionWithValueDisplayed("23"));

        //Then select Java
        javaOption.click();

        //Submit the form
        WebElement CodeInIT = driver.findElement(By.name("submitbutton"));
        CodeInIT.click();

        new WebDriverWait(driver, 10).until(titleIs("Processed Form Details"));
        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java Code", "23", languageWeUsed.getText());
    }

    private ExpectedCondition <WebElement> OptionWithValueDisplayed (final String value) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {

                return webDriver.findElement(By.cssSelector("option[value = '" + value + "']"));
            }
        };
    }
}