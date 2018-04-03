package winiumJavaApp;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class sampleTest {
	
	WiniumDriver driver = null;
    String appPath = "C:/windows/system32/cmd.exe";
    DesktopOptions option = new DesktopOptions();
	
	@Before    
    public void setup() throws MalformedURLException {
		option.setApplicationPath(appPath);
        option.setDebugConnectToRunningApp(false);
        option.setLaunchDelay(2);
        driver = new WiniumDriver(new URL("http://192.168.1.144:9999"),option);
    }

    @Test
    public void testCmd() throws InterruptedException, IOException {
    	
    	Thread.sleep(1000);
        WebElement window =  driver.findElementByName("Text Area");
        window.sendKeys("ipconfig\r");
        System.out.println(window.getText());
        Thread.sleep(1000);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("/home/arios/Imágenes/screenshot.png"));
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
        	driver.close();
        }
    }
	
}