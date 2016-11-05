package com.seleniumsimplified.webdriver.Manipulation;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ManipulationExerciseQ3Test {
    WebDriver driver;

    @Before
    public void StartDriver(){
        driver= Driver.get("http://compendiumdev.co.uk/selenium" + "/basic_html_form.html");
    }

    @Test
    public void Radiobutoon2(){
        WebElement rd2 = driver.findElement(By.cssSelector("[value = 'rd2']"));
        //It's Input not Select
//        Select rd2Selected = new Select(rd2);
//        System.out.println(rd2Selected.getOptions());
        if(!rd2.isSelected()){
            rd2.click();
        }
        assertTrue(rd2.isSelected());
        driver.findElement(By.cssSelector("[value=\"submit\"]")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
        WebElement rd2New = driver.findElement(By.cssSelector("#_valueradioval"));
        assertThat(rd2New.getText(), is("rd2"));
    }
}
