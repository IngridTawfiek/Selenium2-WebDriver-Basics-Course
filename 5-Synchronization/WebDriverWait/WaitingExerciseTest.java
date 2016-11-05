package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class WaitingExerciseTest {

    @Test
    public void CustomExpectedConditions() {
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk//selenium/basic_redirect.html");
        WebElement fiveSeconds = driver.findElement(By.cssSelector("#delaygotobasic"));
        fiveSeconds.click();
        new WebDriverWait(driver, 10).until(titleDoesNotContain("Basic Redirects"));
    }

    private ExpectedCondition<String> titleDoesNotContain(final String title) {
        return new ExpectedCondition<String>() {

            @Override
            public String apply(WebDriver driver) {
                if (driver.getTitle().equals(title)) {
                    return null;
                } else {
                    return title;
                }

            }
            @Override
            public String toString(){
                return "Title Doesnot Contain " + title;
            }
        };
    }

    @Test
    public void CustomExpectedConditions3() {
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk//selenium/basic_redirect.html");
        WebElement fiveSeconds = driver.findElement(By.cssSelector("#delaygotobasic"));
        fiveSeconds.click();
//        new WebDriverWait(driver, 10).until(titleDoesNotContain("Basic Redirects"));
        assertEquals("Basic Web Page Title", new WebDriverWait(driver, 10).until(titleDoesNotContainF("Basic Redirects")));
    }

    private ExpectedCondition <String> titleDoesNotContainF (String stringNotInTitle){
        return new titleDoesNotContain(stringNotInTitle);
    }

    @Test
    public void CustomExpectedConditions2() {
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk//selenium/basic_redirect.html");
        WebElement fiveSeconds = driver.findElement(By.cssSelector("#delaygotobasic"));
        fiveSeconds.click();
        new WebDriverWait(driver, 10).until(new titleDoesNotContain("Basic Redirects"));
    }

    private class titleDoesNotContain implements ExpectedCondition <String>{
        private String titleExcludes;
        private titleDoesNotContain (String notContainthisString){
            this.titleExcludes = notContainthisString;
        }

        @Override
        public String apply(WebDriver webDriver){
            String title = webDriver.getTitle();
            if(title.contains(this.titleExcludes))
                return null;
            return title;
        }
    }

}