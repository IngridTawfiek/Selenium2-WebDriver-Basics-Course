package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait.FluentWait;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FluentWaitExerciseTest {

    @Test
    public void EndWithFluentWait(){
        WebDriver driver = Driver.get("http://compendiumdev.co.uk/selenium/javascript_countdown.html");
        final WebElement CountDown = driver.findElement(By.id("javascript_countdown_time"));
        FluentWait <WebElement> wait = new FluentWait<WebElement>(CountDown).withTimeout(30, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
        wait.until(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement element){
                if (CountDown.getText().endsWith("04"))
                    return CountDown.getText();
                return null;
            }
        });
        assertTrue(CountDown.getText().equals("01:01:04"));
    }

    @Test
    public void EndWithWebDriverWait(){
        WebDriver driver = Driver.get("http://compendiumdev.co.uk/selenium/javascript_countdown.html");
        final WebElement CountDown = driver.findElement(By.id("javascript_countdown_time"));
        String wait = new WebDriverWait(driver,30, 100).until(new ExpectedCondition <String>() {
            @Override
            public String apply(WebDriver driver){
                if (CountDown.getText().endsWith("04"))
                    return CountDown.getText();
                return null;
            }
        });
        assertEquals(wait, "01:01:04");
    }

}
