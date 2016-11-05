package com.seleniumsimplified.webdriver.Synchronization.Cookies;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CookieExerciseTest {

    WebDriver driver;
    WebElement Text;
    WebElement Submit;

    @Before
    public void setup(){

        driver = Driver.get("http://compendiumdev.co.uk/selenium/search.php");
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        refreshPageObjects();
    }

    private void refreshPageObjects(){

        Text = driver.findElement(By.cssSelector("input[type = 'text']"));
        Submit = driver.findElement(By.cssSelector("input[type = 'submit']"));
    }
    @Test
    public void SearchandCheckNoOfVisits(){
        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
//        System.out.println(aCookie);
        Text.clear();
        Text.sendKeys("Ingrid Tawfik");
        Submit.click();
        Cookie nextCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertTrue(Integer.parseInt(nextCookie.getValue()) == Integer.parseInt(aCookie.getValue()) + 1 );
    }

    @Test
    public void SearchandCheckNoOfVisits2(){
//        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
//        System.out.println(aCookie);
        Text.clear();
        Text.sendKeys("Ingrid Tawfik");
        Submit.click();
        Set <Cookie> Cookies = driver.manage().getCookies();
        for (Cookie aCookie : Cookies){
            if(aCookie.getName().contentEquals("seleniumSimplifiedSearchNumVisits")){
                assertEquals(aCookie.getValue(), String.valueOf(2));
            }
        }
    }

    @Test
    public void HackCookie(){

        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Cookie aNewCookie = new Cookie(aCookie.getName(), String.valueOf(42), aCookie.getDomain(),
                aCookie.getPath(), aCookie.getExpiry(), aCookie.isSecure());
        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);
        Text.clear();
        Text.sendKeys("Ingrid Tawfik");
        Submit.click();
        aNewCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("43", aNewCookie.getValue());
    }

    @Test
    public void HackCookie2(){
        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Cookie aNewCookie = new Cookie.Builder(aCookie.getName(), String.valueOf(42)).domain(aCookie.getDomain()).path(aCookie.getPath())
                .expiresOn(aCookie.getExpiry()).isSecure(aCookie.isSecure()).build();
        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);
        Text.clear();
        Text.sendKeys("Ingrid Tawfik");
        Submit.click();
        aNewCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("43", aNewCookie.getValue());
    }

//    @Test
//    public void creatingCookieFromScratch(){
//        Cookie aNewCookie = new Cookie.Builder("seleniumSimplifiedSearchNumVisits", String.valueOf(29)).path("/selenium//").build();
//        driver.manage().deleteCookieNamed("seleniumSimplifiedSearchNumVisits");
//        driver.manage().addCookie(aNewCookie);
//        Text.clear();
//        Text.sendKeys("Ingrid Tawfiek");
//        Submit.click();
//        aNewCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
//        assertEquals("30",
//        System.out.println(aNewCookie.getValue());
///       assertEquals(30, Integer.parseInt(aCookie.getValue()));
//    }

@Test
public void changeCookieVisitsCountSimply(){

    Text.clear();
    Text.sendKeys("Cookie Test");
    Submit.click();

    refreshPageObjects();

    driver.manage().deleteCookieNamed("seleniumSimplifiedSearchNumVisits");

    String path = "/selenium";

    if(Driver.currentBrowser() == Driver.BrowserName.FIREFOX){
        path = path + "/"; // need to add a trailing / for firefox only
    }

    driver.manage().addCookie(
            new Cookie.Builder("seleniumSimplifiedSearchNumVisits",
                    String.valueOf(402)).
                    path(path).build());

    Text.clear();
    Text.sendKeys("Simple Cookie Change Test");
    Submit.click();

    // chromedriver has a bug where the domain is prefixed with '.' therefore
    // the app 'updates' a new cookie so when chromedriver returns the cookie it returns
    // the first and not the updated one
    Cookie aCookie = null;
    if(Driver.currentBrowser()== Driver.BrowserName.GOOGLECHROME){
        aCookie = getCookieWithLargestValueNamed("seleniumSimplifiedSearchNumVisits");
    }else {
        aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
    }
    assertEquals("I should be a very frequent visitor", 403, Integer.parseInt(aCookie.getValue()));
}
    private Cookie getCookieWithLargestValueNamed(String cookieName) {
        int largestCookieVal=0;
        Cookie largestCookie=null;
        for(Cookie aCookie : driver.manage().getCookies()){
            if(aCookie.getName().contentEquals(cookieName)){
                int cookieVal = Integer.parseInt(aCookie.getValue());
                if(cookieVal>largestCookieVal) {
                    largestCookieVal = cookieVal;
                    largestCookie = aCookie;
                }
            }
        }
        return largestCookie;
    }
}
