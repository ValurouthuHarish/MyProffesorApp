package browsertest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.PerformsTouchActions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumCommandLine {
	public static AndroidDriver  driver;
	@BeforeClass
    public void setUp() throws MalformedURLException {
		
		
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.IEXPLORE);
		
		capabilities.setCapability("appPackage","com.climate.farmrise");
		capabilities.setCapability("appActivity","com.climate.farmrise.base.FarmriseHomeActivity ");
		//capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		capabilities.setCapability("noReset","true");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"myandroid");
		capabilities.setCapability(MobileCapabilityType.VERSION,"6.0");
		
		URL url= new URL("http://127.0.0.1:4725/wd/hub");
		if(checkIfServerIsRunnning(4725)) {
			  	stopServer();
		} 
		startServer(); 
		driver = new AndroidDriver(url,capabilities);
		
	   
	  } 
	@Test
	public void testcase01() throws Exception
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[@resource-id='com.climate.farmrise:id/action_more'])")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@resource-id='com.climate.farmrise:id/more_govtSchemes'])")).click();
		
		stopServer();
	}
	public void swipescreen()
	{
		Dimension dim=driver.manage().window().getSize();
		int height=dim.getHeight();
		int width=dim.getWidth();
		
		int startx=width/2;
		int endx=width/2;
		int starty=(int) (height*.40);
		int endy=(int) (height*.10);
		
		TouchAction action=new TouchAction(driver);
		action.press(PointOption.point(startx, starty))
		.moveTo(PointOption.point(endx, endy))
		.release()
		.perform();
	}
	public boolean checkIfServerIsRunnning(int port) {
		  
		  boolean isServerRunning = false;
		  ServerSocket serverSocket;
		  try {
		   serverSocket = new ServerSocket(port);
		   serverSocket.close();
		  } catch (IOException e) {
		   //If control comes here, then it means that the port is in use
		   isServerRunning = true;
		  } finally {
		   serverSocket = null;
		  }
		  return isServerRunning;
	} 
	public static void swipeVertical(double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
	    Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.width * anchorPercentage);
	    int startPoint = (int) (size.height * startPercentage);
	    int endPoint = (int) (size.height * finalPercentage);
	    //new TouchAction(driver).press(anchor, startPoint).waitAction(duration).moveTo(anchor, endPoint).release().perform();

	    //In documentation they mention moveTo coordinates are relative to initial ones, but thats not happening. When it does we need to use the function below
	    new TouchAction(driver).press(PointOption.point(anchor, startPoint)).moveTo(PointOption.point(0,endPoint-startPoint)).release().perform();
	}
	
	public void startServer() {
		  CommandLine cmd = new CommandLine("C:\\Program Files\\Appium\\node.exe");
		  cmd.addArgument("C:\\Program Files\\Appium\\node_modules\\appium\\lib\\server\\main.js");
		  cmd.addArgument("--address");
		  cmd.addArgument("127.0.0.1");
		  cmd.addArgument("--port");
		  cmd.addArgument("4725");
		  
		  DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
		  DefaultExecutor executor = new DefaultExecutor();
		  executor.setExitValue(1);
		  try {
		   executor.execute(cmd, handler);
		   Thread.sleep(10000);
		  } catch (IOException | InterruptedException e) {
		   e.printStackTrace();
		  }
		 }
		 
		 public void stopServer() {
		  Runtime runtime = Runtime.getRuntime();
		  try {
		   runtime.exec("taskkill /F /IM node.exe");
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
}
