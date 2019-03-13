package evolutyz.myproffesor;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
 
public class Launchapp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "MyPhone");
		caps.setCapability("udid", "fbac8c11"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "9.0.4");
		caps.setCapability("autoGrantPermissions", "true");
		caps.setCapability("appPackage", "myprofessor.smartgen.myprofessor");
		caps.setCapability("appActivity", "myprofessor.smartgen.myprofessor.SplashActivity");
		caps.setCapability("autoDismissAlerts", true);
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
				AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
				Thread.sleep(15000);
				driver.navigate().back();
				driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/textpassword1'])")).sendKeys("Ha@sowjanya1");
				driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/login'])")).click();
				driver.findElement(By.xpath("(//*[@resource-id='myprofessor.smartgen.myprofessor:id/em'])")).sendKeys("7997781083");
				
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

	}

}
