package com.seleniumsimplified.webdriver.PageObjects.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class ProcessedFormPage extends SlowLoadableComponent<ProcessedFormPage>{
    private WebDriver driver;
    public ProcessedFormPage (WebDriver aDriver){
        super(new SystemClock(), 10);
        driver = aDriver;
    }

    public ProcessedFormPage(Clock clock, int timeOutInSeconds) {
        super(clock, timeOutInSeconds);
    }

   // public void waitUntilPageIsLoaded() {
//        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
//
//    }

    public String getValueFor(String valueID) {

        WebElement languageWeUsed = driver.findElement(By.id("_value" + valueID));
        return languageWeUsed.getText();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        if(!driver.getTitle().contentEquals("Processed Form Details")){
            throw new Error("Title was not" + " \"Processed Form Details\"," + " title was" + driver.getTitle());
        }

    }
}
