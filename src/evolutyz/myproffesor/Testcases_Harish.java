package evolutyz.myproffesor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class Testcases {
	public static AppiumDriver<MobileElement> driver;
	static ExtentTest test;
	static ExtentReports report;
	@BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {
		
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "MyPhone");
		caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");
		caps.setCapability("autoAcceptAlerts", "true");
		caps.setCapability("appPackage", "myprofessor.smartgen.myprofessor");
		caps.setCapability("appActivity", "myprofessor.smartgen.myprofessor.SplashActivity");
		caps.setCapability("noReset", "true");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			Thread.sleep(7000);
		
	} catch (MalformedURLException e) {
		System.out.println(e.getMessage());
	}
		
	   
	  } 
	public void loginTest() throws Exception
	{
		System.out.println("Entered the login test");
		try {
			test = report.startTest("Login_Test");
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/em'])")).sendKeys("7997781083");
			test.log(LogStatus.PASS, "Entered the UserName");
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/textpassword1'])")).sendKeys("Ha@sowjanya1");
			test.log(LogStatus.PASS, "Entered the Password");
			driver.navigate().back();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/login'])")).click();
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/pro'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "User is able to Login");
			}
			else
			{
				test.log(LogStatus.FAIL, "Test Failed");
			}
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Test Failed with Exception Thrown");

		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority=1)
	public void homescreenvalidation() throws Exception
	{
		try {
			
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/skip'])")).click();
			Thread.sleep(3000);
			test = report.startTest("HomeScreen_Fields_Validation_Test");
			
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear1'])")).isDisplayed()&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear2'])")).isDisplayed()&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear3'])")).isDisplayed()&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear4'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "All the Fields in the home screen are validated");
			}else
			{
				test.log(LogStatus.FAIL, "Test Failed As the fields in the home screen are missing");
			}
			
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Test Failed As Exception thrown"+e.getStackTrace());
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	
	@Test(priority=2)
	public void navbarvalidation() throws Exception
	{
		try {
			test = report.startTest("Navbar_Fields_Validation_Test");
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/b_userrname'])")).isDisplayed())
			{
				Thread.sleep(200);
				test.log(LogStatus.PASS, "Able to Open the Navigation Bar");
				//driver.navigate().back();
			}else
			{
				//driver.navigate().back();
				test.log(LogStatus.FAIL, "Test Failed As User Not able to open the Nav Bar");
				System.out.println("Nav Bar Validation Failed");
			}
			
		}catch(Exception e)
		{
			//driver.navigate().back();
			test.log(LogStatus.FAIL, "Test Failed As User Not able to open the Nav Bar");
			System.out.println("Nav Bar Validation Failed");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 3)
	public void FieldsValidation_navbar() throws Exception
	{
		test = report.startTest("NavBar_FieldsValidation_Test");
		String compareString="  My Account",compareString1="  Upgrade Now",compareString2="  Share",compareString3= "  Notification",compareString4= "  Contact Us",compareString5= "  Logout";
		try {
			//driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/myaccount'])")).getText().equals(compareString)&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/upgrade'])")).getText().equals(compareString1)&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/share'])")).getText().equals(compareString2)&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/notification'])")).getText().equals(compareString3)&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/phone'])")).getText().equals(compareString4)&&driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/logout'])")).getText().equals(compareString5))
			{
				
				Thread.sleep(200);
				test.log(LogStatus.PASS, "Validation of all the Fields is succesful");
				
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.FAIL, "Test Failed As Fields are missing in Nav Panel");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FAIL, "Test Failed As Fields are missing in Nav Panel");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 4)
	public void upgradeNowValidation() throws Exception
	{
		test = report.startTest("UpgradeNow_Validation_Test");
		
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/upgrade'])")).click();
			test.log(LogStatus.PASS, "Clicked on UpgradeOption");
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/final_price'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Clicked on Upgrade in nav bar and Validated the upgrade page");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.FAIL, "Test Failed As upgrade page not opening");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FAIL, "Test Failed As upgrade page not opening with exception thrown");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 5)
	public void navbar_share_Validation() throws Exception
	{
		test = report.startTest("NavBar_Share_Validation_Test");
		
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/share'])")).click();
			test.log(LogStatus.PASS, "Clicked on Share Option");
			//driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/share'])")).click();
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/name'])")).isDisplayed())
			{
				
				test.log(LogStatus.PASS, "Share Page Opened and validated");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.FAIL, "Test Failed As Share page is not validated");
			}
			
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Test Failed As Share page is not validated with Exception");
			driver.navigate().back();
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 6)
	public void navbar_contact_Validation() throws Exception
	{
		test = report.startTest("NavBar_Contact Option_Validation");
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/phone'])")).click();
			test.log(LogStatus.PASS, "Clicked on Contact Option");
			//driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/share'])")).click();
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/textView6'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Contact Page is opened and validated the page");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.FAIL, "Test Failed As Contact Us Page Not Validated with Exception");
			}
			
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Test Failed As Contact Us Page Not Validated"+e.getStackTrace());
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	
	@Test(priority = 7)
	public void navbar_logout_no_Validation() throws Exception
	{
		test = report.startTest("NavBar_Logout_No_option_Validation");
		
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/navbar'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/logout'])")).click();
			test.log(LogStatus.PASS, "Clicked on Logout Option");
			Thread.sleep(500);
			driver.findElement(By.xpath("(//*[@resource-id='android:id/button2'])")).click();
			test.log(LogStatus.PASS, "Clicked on No Option");
			Thread.sleep(500);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear1'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Log out Test Succesful");
				
			}else
			{
				
				test.log(LogStatus.FAIL, "Log Out No option Test Failed");
			}
			
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Log Out No option Test Failed with Exception");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	
	@Test(priority = 8)
	public void share_homescreen_Validation() throws Exception
	{
		test = report.startTest("Share_Homescreen_Validation");
		
		try {
			
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/share1'])")).click();
			test.log(LogStatus.PASS, "Clicked on Navigation Bar");
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/name'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Share page Opened and Validated");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.FAIL, "Share Page is not opening");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FAIL, "Share Page from Home page is not opening with exception");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 9)
	public void my_Library_home_Validation() throws Exception
	{
		test = report.startTest("My_Library_Home_Validation_Test");
		
		try {
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear1'])")).click();
			test.log(LogStatus.PASS, "Clicked on Library Option in the Home Page");
			Thread.sleep(3000);
			if(driver.findElement(By.className("android.widget.TextView")).isDisplayed()&&driver.findElement(By.className("android.widget.TextView")).getText().equals("My Library"))
			{
				test.log(LogStatus.PASS, "Library Page is opened and validated");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.PASS, "Library Page is not opening");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FAIL, "Library Page is not opening with Exception");
		}finally {
			report.endTest(test);
			//stopServer();
		}
	}
	@Test(priority = 10)
	public void my_Performance_home_Validation() throws Exception
	{
		test = report.startTest("My_Performance_Home_Validation");
		
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear2'])")).click();
			test.log(LogStatus.PASS, "Clicked on My Performance Option");
			Thread.sleep(5000);
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/title'])")).isDisplayed())
			{
				test.log(LogStatus.PASS, "My Performance Page is opened and Validated");
				driver.navigate().back();
			}else
			{
				driver.navigate().back();
				test.log(LogStatus.PASS, "My Performance Page is not opened and Failed");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FATAL, "My Performance Page is not opened and Failed with Exception");
		}finally {
			//stopServer();
		}
	}
	@Test(priority = 11)
	public void take_a_test() throws Exception
	{
		test = report.startTest("Take_a_test_Validation");
		
		
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear3'])")).click();
			test.log(LogStatus.PASS, "Clicked on My Performance Option");
			Thread.sleep(3000);
			
			List<MobileElement> profile = driver.findElements(MobileBy.xpath("//android.widget.TextView"));
			profile.get(4).click();; // Clicks on C Programming
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/easy'])")).click();
			Thread.sleep(1000);		
			System.out.println("Page Source XML-Test 1st"+driver.getPageSource());
			driver.navigate().back();
			Thread.sleep(2500);	
			driver.navigate().back();
	}
	@Test(priority = 12)
	public void previous_questions_Subjects() throws Exception
	{
		test = report.startTest("Previous_questions");
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear4'])")).click();
			test.log(LogStatus.PASS, "Clicked On Previous Question Papers");
			Thread.sleep(5000);
			List<MobileElement> profile = driver.findElements(MobileBy.xpath("//android.widget.TextView"));
			if(profile.get(2).getText().equals("ENGINEERING DRAWING")&&profile.get(3).getText().equals("ENGINEERING MATHEMATICS 1")&&profile.get(4).getText().equals("COMPUTER PROGRAMMING")&&profile.get(5).getText().equals("ENGINEERING MECHANICS"))
			{
				test.log(LogStatus.PASS, "All the Subjects are present in the UI and are Validated");
			}else
			{
				test.log(LogStatus.FAIL,"Subjects are Missing");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FATAL, "My Performance Page is not opened and Failed with Exception");
		}finally {
			//stopServer();
		}
		
	}
	@Test(priority = 13)
	public void previous_questions_Open() throws Exception
	{
		test = report.startTest("previous_questions_Open");
		try {
			Thread.sleep(2000);
			List<MobileElement> profile = driver.findElements(MobileBy.xpath("//android.widget.TextView"));
			profile.get(6).click();
			test.log(LogStatus.PASS, "Clicked on the First Question Paper");
			
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/topicname'])")).getText().equals("QUESTIONS"))
			{
				test.log(LogStatus.PASS, "Able to Open the Question Paper");
				Thread.sleep(6000);
				//TouchActions action = new TouchActions(driver);
				//action.scroll(30, 100);
				//action.perform();
				try {
				Dimension dim = driver.manage().window().getSize();
				int height = dim.getHeight();
				int width = dim.getWidth();
				int x = width/2;
				int top_y = (int)(height*0.80);
				int bottom_y = (int)(height*0.20);
				
				TouchAction ts = new TouchAction(driver);
				ts.press(point(x,top_y)).moveTo(point(x,bottom_y)).release().perform();
				
				test.log(LogStatus.PASS,"Able to Scroll till Bottom of the page");
				driver.navigate().back();
				Thread.sleep(3000);
				driver.navigate().back();
				}catch(Exception e)
				{
					driver.navigate().back();
					Thread.sleep(2000);
					driver.navigate().back();
					test.log(LogStatus.FAIL,"Error while scrolling in Question paper");
				}
				


			}else
			{
				test.log(LogStatus.FAIL,"Not Able to Open the Question Paper");
			}
			
		}catch(Exception e)
		{
			driver.navigate().back();
			test.log(LogStatus.FATAL, "Not Able to Open the Question Paper");
		}finally {
			//stopServer();
		}
		
	}
	@Test(priority = 14)
	public void take_a_test_verifyresult() throws Exception
	{
			test = report.startTest("Take_a_test_Validation_verifyresult");
		
			//driver.navigate().back();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/linear3'])")).click();
			test.log(LogStatus.PASS, "Clicked on Take a Test");
			Thread.sleep(3000);
			
			List<MobileElement> profile = driver.findElements(MobileBy.xpath("//android.widget.TextView"));
			profile.get(4).click();; // Clicks on C Programming
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/easy'])")).click();
			Thread.sleep(4000);	
			List<MobileElement> profile1 = driver.findElements(MobileBy.xpath("//android.widget.Button"));
			//END TEST
			//NEXT
			int i=0;
			for(i=0;i<10;i++)
			{
				driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/next'])")).click();	
				Thread.sleep(5000);
			}
			if(i==10)
			{
				driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/end'])")).click();
				Thread.sleep(5000);
			}
			if(driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/topicname'])")).getText().equals("SCORE"))
			{
				test.log(LogStatus.PASS, "Test Completed and Passed");
			}
			
				
			
			
	}
	@AfterClass
	public static void endTest()
	{
		//driver.quit();
		report.flush();
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
	public void startServer() {
		  CommandLine cmd = new CommandLine("C:\\Program Files\\Appium\\node.exe");
		  cmd.addArgument("C:\\Program Files\\Appium\\node_modules\\appium\\lib\\server\\main.js");
		  cmd.addArgument("--addr	ess");
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
		   //startServer();
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
