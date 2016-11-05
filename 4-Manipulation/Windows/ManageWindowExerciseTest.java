package com.seleniumsimplified.webdriver.Manipulation.Windows;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.awt.*;
import org.openqa.selenium.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ManageWindowExerciseTest {
    public static WebDriver driver;
    public static Window Winny;

    @BeforeClass
    public static void Setup(){
        driver = new FirefoxDriver();
        Winny = driver.manage().window();
    }

    @Before
    public void startDriver(){
        driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
    }

    @Test
    public void aMaximizeWindow(){
        System.out.println(Winny.getSize());
        Winny.maximize();
        Dimension fullScreenSize = Winny.getSize();
        java.awt.Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(Winny.getSize());
        System.out.println(Winny.getPosition());
        String expected = "";
        expected = ((int)screenDimension.getWidth()) + " approx (90% tolerance) " + fullScreenSize.getWidth();
        assertTrue(expected, (screenDimension.getWidth()*0.9) < fullScreenSize.getWidth());
        expected = ((int)screenDimension.getHeight()) + " approx (90% tolerance) " + fullScreenSize.getHeight();
        assertTrue(expected, (screenDimension.getHeight()*0.9) < fullScreenSize.getHeight());
}
    @Test
    public void bHalfSizeWindow(){
        System.out.println(Winny.getSize());
        Winny.maximize();
        Dimension fullScreenSize = Winny.getSize();
        Winny.setSize(new Dimension(fullScreenSize.getWidth()/2, fullScreenSize.getHeight()/2));
        assertEquals("Width half equals", Winny.getSize().getWidth(), fullScreenSize.getWidth()/2);
        assertEquals("Height half equals", Winny.getSize().getHeight(), fullScreenSize.getHeight()/2);
        System.out.println(Winny.getSize());
    }


    @Test
    public void CenteredWindow(){
        System.out.println(Winny.getSize());
        Winny.maximize();
        Dimension fullScreenSize = Winny.getSize();
        Winny.setSize(new Dimension(fullScreenSize.getWidth()/2, fullScreenSize.getHeight()/2));
        Winny.setPosition(new Point(fullScreenSize.getWidth()/4, fullScreenSize.getHeight()/4));
    }

    @Test
    public void Bounce(){
        System.out.println(Winny.getSize());
        Winny.maximize();
        Dimension fullScreenSize = Winny.getSize();
        int changeWidth = fullScreenSize.getWidth();
        int changeHeight = fullScreenSize.getHeight();

        while (changeWidth > 100){
            changeWidth = changeWidth - 50;
            if (changeHeight > 200)
                changeHeight = changeHeight - 50;
            Winny.setSize(new Dimension(changeWidth, changeHeight));
            try {Thread.sleep(50);} catch (InterruptedException e){}
        }

        int xDir = 10;
        int yDir = 10;
        Point Position = Winny.getPosition();
        for(int BounceIterations = 0; BounceIterations < 1000; BounceIterations++){
            Position = Position.moveBy(xDir, yDir);
            Winny.setPosition(Position);
            if (Position.getX() > (fullScreenSize.getWidth()-changeWidth)){
                xDir = -10;
            }
            if (Position.getX() < 0){
                xDir = 10;
            }
            if (Position.getY() > (fullScreenSize.getHeight()-changeHeight)){
                yDir = -10;
            }
            if (Position.getY() < 0){
                yDir = 10;
            }
        }


    }

    @Test
    public void Bounce2(){
        double xPos = 0;

        double yPos = 550;

        Winny.setSize(new Dimension(100, 100));

        Winny.setPosition(new Point((int)xPos,(int)yPos));

        while(xPos < 1920) {

            xPos = xPos + 10;

            yPos = (-1 * Math.sin(xPos/100) * 400);

            if(yPos > 0) {

                yPos = -yPos;

            }

            yPos = yPos + 550;

            Winny.setPosition(new Point((int)xPos,(int)yPos));

        }
    }

    @AfterClass
    public static void Quit(){
        driver.quit();
    }
}