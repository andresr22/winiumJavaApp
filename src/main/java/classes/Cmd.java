package classes;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Cmd {
	
	WiniumDriver driver = null;
    String appPath = "C:/windows/system32/cmd.exe";
    DesktopOptions option = new DesktopOptions();
	 
    public void setup(String ip) throws MalformedURLException {
		option.setApplicationPath(appPath);
        option.setDebugConnectToRunningApp(false);
        option.setLaunchDelay(2);
        driver = new WiniumDriver(new URL("http://" + ip + ":9999"),option);
    }

    public void testCmd(String command, boolean screen) throws InterruptedException, IOException {
    	
    	Thread.sleep(1000);
        WebElement window =  driver.findElementByName("Text Area");
        window.sendKeys(command + "\r");
        System.out.println(window.getText());
        Thread.sleep(1000);
        if(screen) {
        	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/home/arios/Imágenes/screenshot.png"));
        }
    }
    
    public void tearDown() {
        if (driver != null) {
        	driver.close();
        }
    }
	
}