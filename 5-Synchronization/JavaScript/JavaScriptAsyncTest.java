package com.seleniumsimplified.webdriver.Synchronization.JavaScript;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class JavaScriptAsyncTest {

    WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void sleepEx(){
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
                long ElapsedTime = System.currentTimeMillis() - start;
        System.out.println(ElapsedTime);
                assertTrue(System.currentTimeMillis() - start > 500);
    }

    @Test
    public void XMLHTTPRequest(){
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  };" +
                        "};" +
                        "xhr.send();");
        System.out.println((String)response);
        assertThat((String)response, containsString("{optionValue:10, optionDisplay: 'C++'"));

    }

}
