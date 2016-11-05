package com.seleniumsimplified.webdriver.Manipulation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManipulationExerciseQ7Test {
        public WebDriver driver;

        @Before
        public void startDriver(){
            driver = new FirefoxDriver();
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        }

        @Test
        public void SubmitAFileTest() throws URISyntaxException {
            WebElement FileNameInput = driver.findElement(By.cssSelector("[name=\"filename\"]"));
            File TextFile = new File(this.getClass().getResource("/Advert- 4 Holden Court.pdf").toURI());
            FileNameInput.sendKeys(TextFile.getAbsolutePath());
            clickSubmitButton();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Processed Form Details"));
            assertSubmitFiletResultIsCorrect();
        }

        @After
        public void quitDriver(){
            driver.close();
        }

        private void clickSubmitButton(){
            WebElement SubmitButton = driver.findElement(By.cssSelector("[value = 'submit']"));
            SubmitButton.click();
        }

        private void assertSubmitFiletResultIsCorrect(){
            assertEquals(driver.findElement(By.id("_valuefilename")).getText(), "Advert- 4 Holden Court.pdf");
        }
}
