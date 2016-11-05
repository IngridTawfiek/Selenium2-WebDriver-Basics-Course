package com.seleniumsimplified.webdriver.Navigation;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class NavigateExerciseTest {

    private static WebDriver driver;
    final private String PROTOCOL = "http";
    final private String DOMAIN = "www.compendiumdev.co.uk";
    final private String ROOT_URL = PROTOCOL + "://" + DOMAIN;

    @BeforeClass
    public static void createDriver(){
        driver = Driver.get();
    }

    @Before
    public void NavigateToEx(){
        driver.navigate().to(ROOT_URL + "/selenium/basic_html_form.html");
        System.out.println(driver.getTitle());
        assertThat("Title should be HTML Form Elements", "HTML Form Elements",is(driver.getTitle()));
    }

    @Test
    public void GetEx(){
        driver.get(ROOT_URL + "/selenium");
        System.out.println(driver.getTitle());
    }

    @Test
    public void BackForwardEx(){
        driver.navigate().to(ROOT_URL + "/selenium/basic_web_page.html");
        System.out.println(driver.getTitle());
        driver.navigate().back();
        System.out.println(driver.getTitle());
        assertFalse("Title should not equal null", driver.getTitle().isEmpty());
        driver.navigate().forward();
        System.out.println(driver.getTitle());
        assertTrue("Title should be Basic Web PAge Title", driver.getTitle().contains("Basic Web Page Title"));
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
    }

    @Test
    public void RefreshEx(){
        driver.navigate().to(ROOT_URL + "/selenium/refresh.php");
        System.out.println(driver.getTitle());
        final String refreshTitleConstant = "Refreshed Page on ";
        assertTrue("Title starts with Refreshed Page on ", driver.getTitle().startsWith(refreshTitleConstant));
        long startTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant,""));
        System.out.println(startTime);
        try{Thread.sleep(2000);}
        catch (InterruptedException e) {}
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        long endTime = Long.parseLong(driver.getTitle().replaceFirst(refreshTitleConstant, ""));
        System.out.println(endTime);
        assertTrue("Expected" + endTime + ">" + startTime, endTime > startTime);
    }

    @Test
    public void NavigatetoURLEx() throws MalformedURLException{
        URL searchPage = new URL(PROTOCOL,DOMAIN,"/selenium/search.php");
        driver.navigate().to(searchPage);
        System.out.println(driver.getTitle());
        assertEquals("Title should be Selenium Simplified Search Engine", "Selenium Simplified Search Engine", driver.getTitle());
    }
}
