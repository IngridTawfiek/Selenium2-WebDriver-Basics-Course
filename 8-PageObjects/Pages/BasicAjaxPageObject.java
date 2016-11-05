package com.seleniumsimplified.webdriver.PageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject>{
   private WebDriver driver;

    @FindBy(how = How.ID, using = "combo1")
    private WebElement categorySelect;

    @FindBy(how = How.ID, using = "Combo2")
    private WebElement languageSelect;

    @FindBy(how = How.NAME, using = "submitbutton")
    private WebElement codeInIt;


    public enum Category {
        WEB(1), DESKTOP(2), SERVER(3);

        private final int dropDownValue;

        Category(int value) {
            this.dropDownValue = value;
        }
        public int value(){
        return dropDownValue;
    }
    }

    public enum Language{
        JAVASCRIPT(1), CPP(10), JAVA(23);


        private final int dropDownLanguageValue;

        Language(int LanguuageValue) {
            this.dropDownLanguageValue = LanguuageValue;
        }
        public int LanguageValue(){
            return dropDownLanguageValue;
        }
    }

    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
        PageFactory.initElements(driver, this);
    }


    @Override
    protected void load() {
        driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

    }

    @Override
    protected void isLoaded() throws Error {
        try {
            categorySelect.isDisplayed();
        } catch (NoSuchElementException e){
            throw new Error("Basic ajax page not loaded");
        }

    }

    public void selectCategory(Category category) {
        categorySelect.findElement(By.cssSelector("option[value='" + category.value() + "']")).click();

//        // wait until the option I want to click is present
        //we could also wait for the contents of the drop down to fill
//        new WebDriverWait(driver,10).until(
//                ExpectedConditions.presenceOfElementLocated(
//                        By.cssSelector("option[value='23']")));

        //instead wait until the ajax symbol has gone
        // because then the drop down has populated
        new WebDriverWait(driver,10).until(AjaxActionComplete());
    }

    public ExpectedCondition<Boolean> AjaxActionComplete() {

        return ExpectedConditions.invisibilityOfElementLocated(
                By.id("ajaxBusy"));
    }

    public void selectLanguage(Language language) {
        languageSelect.findElement(By.cssSelector("option[value='" + language.LanguageValue() + "']")).click();
    }

    public void clickCodeInIt() {
        codeInIt.click();
    }

}
