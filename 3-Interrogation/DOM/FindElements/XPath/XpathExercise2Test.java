package com.seleniumsimplified.webdriver.Interrogate.DOM.FindElements.XPath;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class XpathExercise2Test {
    public static WebDriver driver;

    @BeforeClass
    public static void initiateDriver(){
        driver = Driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void FindByCssDataDriven(){

        class TestData {
            public String xpath;
            public String name;
            public String value;
            public org.openqa.selenium.By alt;

            public TestData (String xpath, String name, String value, org.openqa.selenium.By alternativeTo) {
                this.xpath = xpath;
                this.name = name;
                this.value = value;
                this.alt = alternativeTo;
            }
        }

        List<TestData> dataItems = Arrays.asList(
                new TestData("//*[@id='p31']", "name", "pName31", By.id("p31")),
                new TestData("//p[@id='p31']", "name", "pName31", By.id("p31")),
                new TestData("//*[contains(@id,'p31')]",  "name", "pName31", By.id("p31")),
                new TestData("//*[@name= 'ulName1']", "id", "ul1", By.name("ulName1")),
                new TestData("//ul[@name= 'ulName1']", "id", "ul1", By.name("ulName1")),
                new TestData("//*[contains(@name, 'ulName1')]", "id", "ul1", By.name("ulName1")),
                new TestData("//*[@class='specialDiv']", "id", "div1", By.className("specialDiv")),
                new TestData("//div[@class='specialDiv']", "id", "div1", By.className("specialDiv")),
                new TestData("//*[contains(@class,'specialDiv') and string-length(@class) = 10]", "id", "div1", By.className("specialDiv")),
                new TestData("//li", "name", "liName1", By.tagName("li")));

        WebElement element;
        for (TestData dataItem : dataItems){
            //        element = driver.findElement(dataItem.alt);
            element = driver.findElement(By.xpath(dataItem.xpath));
            System.out.println(String.format("Instead of %s use By.xpath(\"%s\")", dataItem.alt, dataItem.xpath));
            assertThat(element.getAttribute(dataItem.name), is(dataItem.value));
            System.out.println(element.getText());
        }

    }

}
