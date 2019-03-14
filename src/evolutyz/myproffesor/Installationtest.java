package evolutyz.myproffesor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
 
public class Installationtest {
	public static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		
			installApp();
			System.out.println("Hai 1");
			//Thread.sleep(120000);
			System.out.println("Hai 2");
			launchApp();
		

	}
	public static void installApp() throws IOException, InterruptedException {
		//Process p1 = Runtime.getRuntime().exec("adb kill-server");
		//Process p2 = Runtime.getRuntime().exec("adb start-server");
		Process p3 = Runtime.getRuntime().exec("cmd /c cd C:\\Users\\harish\\Desktop\\My Proffesor&&adb install mypofessor(13-03-2019).apk");
		p3.waitFor();
		System.out.println("Hai 3");
		//Process p4 = Runtime.getRuntime().exec("adb kill-server");
		Thread.sleep(10000);
	}
	public static void uninstallApp() throws IOException {
		Process runtime = Runtime.getRuntime().exec("adb uninstall myprofessor.smartgen.myprofessor");
	}
	public static void launchApp() throws InterruptedException
	{
		Thread.sleep(7000);
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

}
