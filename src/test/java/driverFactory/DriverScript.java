package driverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commanFunctions.FunctionLibrary;
import utilities.ExelFileUtil;

public class DriverScript {

 
String Module_Status;
	WebDriver driver;
	String inputpath = "./FileInput/Sample.xlsx";
	String outputpath ="./FileOutput/result.xlsx";
	ExtentReports report;
	ExtentTest logger;
	String testcases = "mastertestcases";
	
	public void startTest() throws Throwable {
		
		ExelFileUtil xl = new ExelFileUtil(inputpath);
		
		for(int i=1;i<xl.rowCount(testcases);i++) {
			
			if(xl.getCellData(testcases, i, 2).equalsIgnoreCase("Y")) {
				
				String TCModule=xl.getCellData(testcases, i, 1);
				report = new ExtentReports("./target/ExtentReports/"+TCModule+FunctionLibrary.date()+".html");
				logger = report.startTest(TCModule);
				logger.assignAuthor("aman");
				
				for(int j=1;j<=xl.rowCount(TCModule);j++) {
					
					String Discription = xl.getCellData(TCModule, j, 0);
					String Objecttype = xl.getCellData(TCModule, j, 1);
					String Lname = xl.getCellData(TCModule, j, 2);
					String Lvalue = xl.getCellData(TCModule, j, 3);
					String Testdata = xl.getCellData(TCModule, j, 4);
					
					try {
						if (Objecttype.equalsIgnoreCase("startBrowser")) {
							driver = FunctionLibrary.startBrowser();
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("openurl")) {
							FunctionLibrary.launchUrl();
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("waitforelement")) {
							FunctionLibrary.eleWait(Lname, Lvalue, Testdata);
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("typeaction")) {
							FunctionLibrary.typeAction(Lname, Lvalue, Testdata);
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("clickaction")) {
							FunctionLibrary.clickAction(Lname, Lvalue);
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("validatetitile")) {
							FunctionLibrary.getTitle(Testdata);
							logger.log(LogStatus.INFO, Discription);
						}
						if (Objecttype.equalsIgnoreCase("closebrowser")) {
							FunctionLibrary.closeBrowser();
							logger.log(LogStatus.INFO, Discription);
						}
						
						
						if (Objecttype.equalsIgnoreCase("mouseClick")) {
							FunctionLibrary.mouseClick();
							logger.log(LogStatus.INFO, Discription);
							
						}

						if (Objecttype.equalsIgnoreCase("catagoryTable")) {
							FunctionLibrary.categoryTable(Testdata);
							logger.log(LogStatus.INFO, Discription);
							
						}

						if (Objecttype.equalsIgnoreCase("dropDownAction")) {
							FunctionLibrary.dropDownAction(Lname, Lvalue, Testdata);
							logger.log(LogStatus.INFO, Discription);
						}
						
						if (Objecttype.equalsIgnoreCase("captureStock")) {
							FunctionLibrary.captureStock(Lname, Lvalue);
							logger.log(LogStatus.INFO, Discription);
						}
						
						if (Objecttype.equalsIgnoreCase("dropDownAction")) {
							FunctionLibrary.stockTable();
							logger.log(LogStatus.INFO, Discription);
						}
						
						
							
							
							
							
							
							
							
							
							
							
							
							
							
							
						xl.setCellData(TCModule, j, 5, "pass", outputpath);
						logger.log(LogStatus.PASS, Discription);
						Module_Status="true";
						
						
						
						
					}catch(Exception e) {
						System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "fail", outputpath);
						logger.log(LogStatus.FAIL, Discription);
						Module_Status="false";
					}
					if(Module_Status.equalsIgnoreCase("true")) {
						xl.setCellData(testcases, i, 3, "pass", outputpath);
					}else {
						xl.setCellData(testcases, i, 3, "fail", outputpath);
					}
						report.endTest(logger);
						report.flush();
						
				
				}
				
				
			}else {
				
				xl.setCellData(testcases, i, 3, "blocked", outputpath);
			}
		}
		
		
		
	}
	
	

}
