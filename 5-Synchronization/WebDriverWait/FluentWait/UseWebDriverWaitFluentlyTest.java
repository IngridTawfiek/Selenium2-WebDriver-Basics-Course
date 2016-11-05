package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait.FluentWait;

import com.seleniumsimplified.webdriver.manager.Driver;
import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

public class UseWebDriverWaitFluentlyTest {

    @Test
    public void wait5Milliseconds() {

        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk//selenium/basic_redirect.html");
        long currTime = System.currentTimeMillis();

        try {
            new WebDriverWait(driver, 1).pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(IllegalStateException.class)
                    .withTimeout(5, TimeUnit.SECONDS).withMessage("See I told you a TimeOut happened").until(
                    new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver webDriver) {
                            throw new IllegalStateException();
                        }
                    }
            );
            fail("A timeout Exception should have been thrown");
        } catch (TimeoutException e) {
            assertTrue(e.getMessage().contains("See I told you a TimeOut happened"));
        }
        long nowTime = System.currentTimeMillis();
        Assert.assertTrue((nowTime-currTime) > 5000);
    }
}