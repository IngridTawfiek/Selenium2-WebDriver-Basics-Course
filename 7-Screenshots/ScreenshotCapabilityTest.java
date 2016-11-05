package com.seleniumsimplified.webdriver.Screenshots;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ScreenshotCapabilityTest {

    WebDriver driver;

    @Test
    public void fireFoxGetCapabilitiesScreenshot() throws IOException{
        driver = new FirefoxDriver();
        driver.get("http://seleniumsimplified.com");
        if(((HasCapabilities)driver).getCapabilities().is(
                CapabilityType.TAKES_SCREENSHOT)){
            TakesScreenshot snapper = (TakesScreenshot) driver;
            String tempScreenshot = snapper.getScreenshotAs(OutputType.BASE64);
            Base64 decoder = new Base64();
            byte[] imgBytes = (byte[]) decoder.decode(tempScreenshot);
            File myScreenshotDirectory = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeBase64" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";
            File myScreenshot = new File(myScreenshotDirectory, newImageFileName);
            FileOutputStream asf = new FileOutputStream(myScreenshot);
            asf.write(imgBytes);
            asf.flush();
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
            System.out.println("Temp file written to" + myScreenshot.getAbsolutePath());
            driver.get("file://" + myScreenshot.getAbsolutePath());
        }
    else{
        fail("Driver did not support screenshots");
    }
    }

    @Test
    public void fireFoxGetCapabilitiesScreenshotFile() throws IOException{
        driver = new FirefoxDriver();
        driver.get("http://seleniumsimplified.com");
        if(((HasCapabilities)driver).getCapabilities().is(
                CapabilityType.TAKES_SCREENSHOT)){
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);
            System.out.println(tempScreenshot.getAbsolutePath());
            File myScreenshotDirectory = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeFile" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    "." +
                    getExtension(tempScreenshot);
            File myScreenshot = new File(myScreenshotDirectory, newImageFileName);
            FileUtils.moveFile(tempScreenshot, myScreenshot);
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
            driver.get("file://" + myScreenshot.getAbsolutePath());

        }
        else{
            fail("Driver did not support screenshots");
        }
    }



    @Test
    public void HTMLUnitGetCapabilitiesScreenshot() throws IOException{
        driver = new HtmlUnitDriver();
        driver.get("http://seleniumsimplified.com");
        if(((HasCapabilities)driver).getCapabilities().is(
                CapabilityType.TAKES_SCREENSHOT)){
            fail("Driver did not support screenshots");}
    }

    @Test
    public void fireFoxCastWithException() throws IOException{
        driver = new FirefoxDriver();
        driver.get("http://seleniumsimplified.com"); try{
            TakesScreenshot snapper = (TakesScreenshot) driver;
            byte[] tempScreenshot = snapper.getScreenshotAs(OutputType.BYTES);
            File myScreenshotDirectory = createATempDirectoryForScreenshots();
            String newImageFileName = "persistOutputTypeByte" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";
            File myScreenshot = new File(myScreenshotDirectory, newImageFileName);
            FileOutputStream osf = new FileOutputStream(myScreenshot);
            osf.write(tempScreenshot);
            osf.flush();
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
            System.out.println("temp file written to" + myScreenshot.getAbsolutePath());
            driver.get("file://" + myScreenshot.getAbsolutePath());

        }catch(ClassCastException e){
            System.out.println(e);
            fail("Driver did not support screenshots");
        }
        }


    private File createATempDirectoryForScreenshots() {
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%stest%sresources%stemp%sscreenshots",s,s,s,s,s);

        File testTempDir = new File(ourTestTempPathName);
        if(testTempDir.exists()){
            if(!testTempDir.isDirectory()){
                fail("Test path exists but is not a directory");
            }
        }else{
            testTempDir.mkdirs();
        }

        return testTempDir;
    }

    private String getExtension(File fileWithExtension) {
        String fileName = fileWithExtension.getName();
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    @Test
    public void HTMLUnitCastWithException() throws IOException{
        driver = new HtmlUnitDriver();
        driver.get("http://seleniumsimplified.com");
        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            fail("Expected htmlunit to not cast to takes_screenshot");
        }catch(ClassCastException e){
            System.out.println(e);
        }
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
