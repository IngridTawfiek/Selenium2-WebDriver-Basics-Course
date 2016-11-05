package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FirstFindElementByExerciseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void StartDriver(){
       driver = new FirefoxDriver();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void noSuchElementException_try_catch(){
        try {
            WebElement cParagraph = driver.findElement(By.id("p3name"));
            fail("Expected NoSuchElementExpection");
        }
        catch (NoSuchElementException e) {
            System.out.println("Caught exception NoSuchElementException");
        }
    }

    @Test
    public void findById(){
        WebElement div19 = driver.findElement(By.id("div19"));
        System.out.println(div19.getAttribute("className"));
        assertThat("class name of id div19 is 'this has multiple values'",
                div19.getAttribute("className"), is("this has multiple values"));
        System.out.println(div19.getText());
        assertTrue("Text of id div16 should equal 'within div of multiple class styles'",
                div19.getText().equals("within div of multiple class styles\n" +
                        "  "));
    }

    @Test
    public void findByLinkText(){
        WebElement jumptopara22 = driver.findElement(By.linkText("jump to para 22"));
        System.out.println(jumptopara22.getTagName());
        assertThat("Text of link Text should be jump to para 22", jumptopara22.getText(), is("jump to para 22"));
    }

    @Test
    public void findByName(){
        WebElement nestedDiv = driver.findElement(By.name("pName29"));
        System.out.println(nestedDiv.getText());
        assertEquals("id should be equal p29", "p29", nestedDiv.getAttribute("id"));
    }

    @Test
    public void findByPartialLinkText(){
        WebElement jumpto = driver.findElement(By.partialLinkText("jump to "));
        System.out.println(jumpto.getAttribute("name"));
        assertTrue("Text of link Text should go to first jump to ", jumpto.getText().equals("jump to para 0"));

        jumpto = driver.findElement(By.partialLinkText("to"));
        System.out.println((jumpto.getSize()));
        assertThat("Middle partial link text", jumpto.getAttribute("href"), containsString("#pName0"));

        jumpto = driver.findElement(By.partialLinkText("5"));
        System.out.println(jumpto.isDisplayed());
        assertEquals("5 not 15", "jump to para 5", jumpto.getText());
    }

    @Test
    public void findByClassName(){
        WebElement className = driver.findElement(By.className("normal"));
        System.out.println(className.getLocation());
        assertTrue("class Name normal has many values", className.getAttribute("href")==null);
    }

    @AfterClass
    public static void closeDriver(){
        driver.close();
    }

}
