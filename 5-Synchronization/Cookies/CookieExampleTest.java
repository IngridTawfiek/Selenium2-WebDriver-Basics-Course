package com.seleniumsimplified.webdriver.Synchronization.Cookies;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class CookieExampleTest {

    @Test
    public void visitSearchPageAndCheckNoLastSearchCookie(){
        WebDriver driver;
        driver = Driver.get("http://compendiumdev.co.uk/selenium/search.php");
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        Cookie aCookie = driver.manage().getCookieNamed("SeleniumSimplifiedLastSearch");
        Cookie.Builder anotherNewCookie = new Cookie.Builder("myCookie", "myvalue");
        anotherNewCookie.build();
        assertEquals("Should be no last search cookie", null, aCookie);
    }
}
