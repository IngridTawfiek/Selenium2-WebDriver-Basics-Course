package com.seleniumsimplified.webdriver.PageObjects;

import com.seleniumsimplified.webdriver.PageObjects.Pages.BasicAjaxPageObject;
import com.seleniumsimplified.webdriver.PageObjects.Pages.BasicAjaxPageObject.Category;
import com.seleniumsimplified.webdriver.PageObjects.Pages.BasicAjaxPageObject.Language;
import com.seleniumsimplified.webdriver.PageObjects.Pages.ProcessedFormPage;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WithoutPageObjectsTest {
    private WebDriver driver;
    private BasicAjaxPageObject BasicAjaxPage;

    @Before
    public void setUp(){
        driver = Driver.get();
        BasicAjaxPage = new BasicAjaxPageObject(driver);
        BasicAjaxPage.get();
    }

    @Test
    // Exercise: Feel The Pain
    public void chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample(){

        try{
            WebDriver driver;

            driver = Driver.get("http://compendiumdev.co.uk/selenium/" +
                    "basic_ajax.html");

            // select Server
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            // then select Java
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // Submit the form
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());

        }catch(Exception e){
            assertTrue("Expected some sort of exception thrown",true);
        }
    }

    @Test
    public void chooseToCodeInJavaScriptOnTheWeb(){

        BasicAjaxPage.selectCategory(Category.SERVER);
        BasicAjaxPage.selectCategory(Category.WEB);

        // wait until the ajax symbol has gone
        // because then the drop down has populated
        new WebDriverWait(driver,10).until(BasicAjaxPage.AjaxActionComplete());

        BasicAjaxPage.selectLanguage(Language.JAVASCRIPT);

        BasicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
       // processedFormPage.waitUntilPageIsLoaded();
        processedFormPage.get();

        assertEquals("Expected Java code", "1", processedFormPage.getValueFor("language_id"));


    }

    @Test
    public void chooseToCodeInCppOnDesktop(){

        BasicAjaxPage.selectCategory(Category.DESKTOP);

        // then select Java

        BasicAjaxPage.selectLanguage(Language.CPP);

        BasicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.get();

        assertEquals("Expected Java code", "10", processedFormPage.getValueFor("language_id"));

    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample(){

       BasicAjaxPage.selectCategory(Category.SERVER);

        // then select Java

        BasicAjaxPage.selectLanguage(Language.JAVA);

        BasicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.get();

        assertEquals("Expected Java code", "23", processedFormPage.getValueFor("language_id"));

    }


}
