package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class FindElementsExerciseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void initiateDriver() {
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void divEx() {
        List<WebElement> elements;
        elements = driver.findElements(By.tagName("div"));
        System.out.println(elements.size());
        assertTrue("there are 19 div elements", elements.size() == 19);
    }

    @Test
    public void hrefEx() {
        List<WebElement> elements1;
        elements1 = driver.findElements(By.cssSelector("a"));
        System.out.println(elements1.size());
        int href = 0;
        for (WebElement e : elements1) {
            if (e.getText().contains("para") && e.getAttribute("href") != null) {
                href++;
            }
        }
        System.out.println(href);
        assertEquals(25, href);
    }
//
//    @Test
//    public void ByChained(){
//        WebElement element2;
//        element2 = driver.findElement (new ByChained (By.tagName("a"), By.partialLinkText("para")));
//        System.out.println(element2);
  //      assertTrue(element2.getText().contains("para"));
//
//    }

//    @Test
//    public void NestedDiv(){
//        List<WebElement> elements1;
//        elements1 = driver.findElements(By.cssSelector("a"));
//       Set <String> href = new HashSet<String>();
//        for(WebElement e:elements1){
//            if (e.getAttribute("href")!= null){
//                href.add(e.getText());
//             }
//             }
//        System.out.println(href.size());
//
//        int text = 0;
//        for(int i=1; i<=href.size(); ++i) {
//            if (href[i] = "para"){
//                text ++;
//            }
//        }
//        assertEquals(25, text);
//    }

        @Test
        public void nestedPAra() {
            List<WebElement> elements;
            elements = driver.findElements(By.tagName("p"));
            int nestedCount = 0;
            for (WebElement e : elements) {
                if (e.getText().contains("nested para")) {
                    nestedCount++;
                }
            }
            System.out.println(nestedCount);
            assertEquals(16, nestedCount);
            assertEquals(41, elements.size());
        }

    public static class FindElementsTest {

        public static WebDriver driver;

        @BeforeClass
        public static void StartAndNavigateDriver() {
            driver = new FirefoxDriver();
            driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
        }

        @Test
        public void returnListOfElementsByClassName() {
            List<WebElement> elements;
            elements = driver.findElements(By.className("normal"));
            Set<java.lang.String> foundtags = new HashSet<java.lang.String>();
            for (WebElement e : elements) {
                foundtags.add(e.getTagName());
            }
            System.out.println(foundtags);
            assertTrue("expected p Tag", foundtags.contains("p"));
            assertTrue("expected a Tag", foundtags.contains("a"));
            assertTrue("expected pre Tag", foundtags.contains("pre"));
            assertTrue("expected u1 Tag", foundtags.contains("ul"));
            assertTrue("expected li Tag", foundtags.contains("li"));
            assertFalse("expected div Tag", foundtags.contains("div"));
        }

        @AfterClass
        public static void quitDriver(){
            driver.quit();
        }
    }
}
