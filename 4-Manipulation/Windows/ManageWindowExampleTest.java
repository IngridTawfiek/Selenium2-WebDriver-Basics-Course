package com.seleniumsimplified.webdriver.Manipulation.Windows;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ManageWindowExampleTest {

    @Test
    public void ManageWindow(){
    WebDriver driver = new FirefoxDriver();
    driver.get("http://compendiumdev.co.uk/selenium/frames");
        Window Winny = driver.manage().window();
        Winny.setPosition(new Point(10, 20));
        Point p = Winny.getPosition();
        assertEquals(p.getX(), 10);
        assertTrue(p.getY() == 20);
        Winny.setSize(new Dimension(350, 400));
        Dimension D = Winny.getSize();
        assertThat(D.getWidth(), is(350));
        assertThat(D.getHeight(), is(400));
        driver.quit();
    }
}
