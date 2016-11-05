package com.seleniumsimplified.webdriver.Synchronization.WebDriverWait.FluentWait;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FluentWaitExampleTest {

    @Test
    public void ExampleUsingExpectedConditions(){

        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
        FluentWait <WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NotFoundException.class);
        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
    }

    @Test
    public void exampleUsingExpectedContionsMix(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).pollingEvery(100, TimeUnit.MILLISECONDS);
        wait.ignoring(NotFoundException.class);
        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
    }

    @Test
    public void fluentWaitDoesnotNeedWebDriverWait(){
        Long startTime = System.currentTimeMillis();
        FluentWait <Long> wait = new FluentWait<Long>(startTime).withTimeout(7, TimeUnit.SECONDS).pollingEvery(50, TimeUnit.MILLISECONDS);
        wait.until(new Function<Long, Long>() {
            @Override
            public Long apply(Long startTime){
            Long currTime = System.currentTimeMillis();
                if(currTime > (startTime + 4000))
                    return currTime;
                return null;
            }
        });
    }
}
