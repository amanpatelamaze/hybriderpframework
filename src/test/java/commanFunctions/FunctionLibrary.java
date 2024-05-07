package commanFunctions;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary {

	public static Properties conpro;
	public static WebDriver driver;

	public static WebDriver startBrowser() throws Throwable{

		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));

		if(conpro.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();


		}else if(conpro.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		}else {
			Reporter.log("browser not mTCH",true);
		}
		return driver;



	}

	public static void launchUrl() {
		driver.get(conpro.getProperty("Url"));
	}

	public static void eleWait( String LocatorName,String LocatorValue,String testData) {

		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData)));
		if (LocatorName.equalsIgnoreCase("xpath")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}

		if (LocatorName.equalsIgnoreCase("id")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}

		if (LocatorName.equalsIgnoreCase("name")) {
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}


	}
	public static void typeAction(String LocatorName,String LocatorValue,String testData) {
		if (LocatorName.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(testData);
		}

		if (LocatorName.equalsIgnoreCase("name")) {
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(testData);
		}

		if (LocatorName.equalsIgnoreCase("id")) {
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(testData);
		}
	}
	public static void clickAction(String LocatorName,String LocatorValue) {
		if (LocatorName.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		if (LocatorName.equalsIgnoreCase("name")) {
			driver.findElement(By.name(LocatorValue)).click();
		}
		if (LocatorName.equalsIgnoreCase("id")) {
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);;
		}
	}
	public static void getTitle(String expectedTitle) {

		String actual = driver.getTitle();
		try
		{
			Assert.assertEquals(actual, expectedTitle,"not match");

		}catch(AssertionError a)
		{
			System.out.println(a.getMessage());
		}
	}
	public static void closeBrowser() {
		driver.quit();
	}
	public static String date() 
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
		return df.format(date);
	}
	
	 public static void mouseClick() throws InterruptedException
	 {
		 Actions ac = new Actions(driver);
		 ac.moveToElement(driver.findElement(By.xpath("//a[starts-with(text(),'Stock Items')]"))).perform();
		 Thread.sleep(3000);
		 ac.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Stock Categories')])[2]"))).click().perform();
		 Thread.sleep(3000);
		 
	 }
	 public static void categoryTable(String Exp_Data) throws InterruptedException {
		 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
			 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
		 
		 
		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();

		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys("TestData");
		 Thread.sleep(3000);
		 String ActData = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[2]/td[9]/div/span/span")).getText();
		 Reporter.log(Exp_Data+"    "+ActData,true);
		 try {
			 Assert.assertEquals(ActData,Exp_Data,"not matching");
		 }catch(AssertionError a)
		 {
			 Reporter.log(a.getMessage(),true);
		 }
		 
		 
		 
	 }
	 public static void dropDownAction(String LocatorName,String LocatorValue,String testData)
	 {
		 if (LocatorName.equalsIgnoreCase("xpath"))
		 {
			 int value = Integer.parseInt(testData);
			 Select element = new Select(driver.findElement(By.xpath(LocatorValue)));
			 element.selectByIndex(value);
			 
		 }
		 
		 if (LocatorName.equalsIgnoreCase("name"))
		 {
			 int value = Integer.parseInt(testData);
			 Select element = new Select(driver.findElement(By.name(LocatorValue)));
			 element.selectByIndex(value);
			 
		 }
		 
		 if (LocatorName.equalsIgnoreCase("id"))
		 {
			 int value = Integer.parseInt(testData);
			 Select element = new Select(driver.findElement(By.id(LocatorValue)));
			 element.selectByIndex(value);
			 
		 }
	 }
	 public static void captureStock(String LocatorName,String LocatorValue) throws IOException {
		 String stockNumber="";
		 if (LocatorName.equalsIgnoreCase("xpath"))
		 {
			stockNumber=driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
			 
		 }
		 
		 if (LocatorName.equalsIgnoreCase("name"))
		 {
			 stockNumber=driver.findElement(By.name(LocatorValue)).getAttribute("value");
		 }
		 
		 if (LocatorName.equalsIgnoreCase("id"))
		 {
			 stockNumber=driver.findElement(By.id(LocatorValue)).getAttribute("value");
		 }
		 FileWriter fw= new FileWriter("./CaptureData/stocknumber.txt");
		 BufferedWriter bw = new BufferedWriter(fw);
		 bw.write(stockNumber);
		 bw.flush();
		 bw.close();
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 public static void stockTable() throws IOException, InterruptedException {
		 
		 FileReader fw= new FileReader("./CaptureData/stocknumber.txt");
		 BufferedReader	 bw = new BufferedReader(fw);
		 String Exp_Data= bw.readLine();
		 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
			 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
		 
		 
		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();

		 driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys("Exp_Data");
		 Thread.sleep(3000);
		 String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[2]/td[9]/div/span/span")).getText();
		 Reporter.log(Act_Data+"    "+Exp_Data,true);
		 try {
		 Assert.assertEquals(Act_Data, Exp_Data,"not match");
		 }catch (AssertionError a)
		 {
			 Reporter.log(a.getMessage(),true);
			  
			 
		 }
		 
		 
		 
	 }


























}
