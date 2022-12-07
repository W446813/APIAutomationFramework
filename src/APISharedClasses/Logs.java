package APISharedClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class Logs  {

	LocalConfiguration logPath = new LocalConfiguration();
	SimpleDateFormat fileDate = new SimpleDateFormat("MMMMM_dd_yyyy");
	Date currentdate = new Date();
	String  testType = logPath.getConfigurationValue("regression"),
			type = logPath.getConfigurationValue("type"),
			fileEnd = logPath.getConfigurationValue("fileend"),
			env = logPath.getConfigurationValue("env"),
			fileDateSTR = fileDate.format(currentdate);
	Boolean regression = Boolean.valueOf(testType); //set true for regression and smoke test or false for testing system
	//If you need to re-enable screenshot uncomment line 198 and line 35
	//ScreenCapture captureScreen = new ScreenCapture();

	public Logs() throws IOException{
		this.startReport();
	}

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public final String PASS = "Test Pass";
	public final String INFO = "Test Info";
	public final String FAIL = "Test Fail";

	public Logs CapturedLogs(String passFailInfo, String keptLogText) throws Exception {
		//TODO Decide to keep non xtm version or not if not delete these sections
//			DateTimeFormatter dtfFile = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH");
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//			LocalDateTime dateNow = LocalDateTime.now();
		//TODO Decide to keep non xtm version or not if not delete these sections

		HashMap<String, String> logText = new HashMap<>();
		logText.put(passFailInfo, keptLogText);

		//TODO Want to see all logs in console? Uncomment this section old old way to do it
//			 for (Iterator<HashMap.Entry<String, String>> itr =
//			 logText.entrySet().iterator(); itr.hasNext();) {
//			 HashMap.Entry<String, String> entry = itr.next();
//			 System.out.println(" " + entry.getKey() + ", " + entry.getValue());
		//TODO Want to see all logs in console? Uncomment this section old old way to do it

		if (passFailInfo.equalsIgnoreCase(PASS)) {
			// testResult = resultPass;
			testPassed(passFailInfo, keptLogText);
		}

		if (passFailInfo.equalsIgnoreCase(INFO)) {
			// testResult = resultInfo;
			testInfo(passFailInfo, keptLogText);
		}

		if (passFailInfo.equalsIgnoreCase(FAIL)) {
			// testResult = resultFail;
			testFail(passFailInfo, keptLogText);
		}
		//TODO Want to see all logs in console? Uncomment this section old old way to do it
//			 }
		//TODO Want to see all logs in console? Uncomment this section old old way to do it

		//TODO Decide to keep non xtm version or not if not delete these sections
//			try (FileWriter indexFile = new FileWriter(getDirPath() + dtfFile.format(dateNow) + "_" + PhonePicker.AppName()
//					+ "_" + PhonePicker.PhoneID().toString() + "_" + "DriverDash_index.html", true)) {
//				BufferedWriter out = new BufferedWriter(indexFile);
//				out.write(dtf.format(dateNow) + " " + passFailInfo + " " + keptLogText + "  " + "<br/>");
//				out.close();
//			}
		//TODO Decide to keep non xtm version or not if not delete these sections

		this.tearDown();
		return this;
	}




	public void startReport() {
		LocalConfiguration logPath = new LocalConfiguration();

		//String env;
		//String env = logPath.getConfigurationValue("env");

		File directory = new File(getDirPath());
		if (!directory.exists()) {
			System.out.println("Test directory creation initated");
			directory.mkdir();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
		} else {
			//System.out.println("Directory already exists");
		}

		if (extent == null) {
			System.out.println("initialize the HtmlReporter");
			// initialize the HtmlReporter

			// builds a new report using the html template
			ExtentHtmlReporter htmlReporter;

			//DriverDash/September_16_2022/SIT_PR%20_September_16_2022_09-32_DriverDash.html


			if (regression == true) {
				//htmlReporter = new ExtentHtmlReporter(getDirPath()+ "Test_Name_"+ buildNum + new SimpleDateFormat("MMMMM_dd_yyyy_HH-mm").format(currentdate) + "_" + "DriverDash_SmokeTest.html");
				htmlReporter = new ExtentHtmlReporter(getDirPath()+env + this.type + new SimpleDateFormat("HH-mm").format(currentdate) + "_" +fileEnd+".html");

				//htmlReporter = new ExtentHtmlReporter(getDirPath()+ this.testName + this.type + this.buildNum + new SimpleDateFormat("MMMMM_dd_yyyy_HH-mm").format(currentdate) + "_" + ".html");

				//htmlReporter = new ExtentHtmlReporter(getDirPath()+ this.type + this.buildNum + "_" + ".html");


			} else {
				htmlReporter = new ExtentHtmlReporter(getDirPath()+env + "System_test_" + new SimpleDateFormat("HH-mm").format(currentdate) + "_" +fileEnd+".html");

			}



			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			htmlReporter.config().setChartVisibilityOnOpen(true);
			//htmlReporter.config().setDocumentTitle("EFS Automation Report");
			htmlReporter.config().setReportName(fileEnd);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			//htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			// htmlReporter.setAppendExisting(true);
		}

	}
	//change to create test
	public void setupTest(String desc) {
		test = extent.createTest(desc);
	}

	public void setupTestYellow(String desc ) {
		test = extent.createTest("<b><font color = yellow>" + desc + "</font></b>");
	}

	public void setupTestClass(String desc ) {
		test = extent.createTest("<b><font color = orange>" + desc + "</font></b>");
	}

	public void setupTestWhiteInfo(String desc ) {
		test = extent.createTest("<font color = #FFFFFF>" + desc + "</font>");
	}

	public void setupTestCICD(String desc ) {
		test = extent.createTest("<h5><font color = #46BFBD>" + desc + "</font></h5>");
	}

	public void setupTestRed(String desc ) {
		test = extent.createTest("<font color = red>" + desc + "</font>");
	}


	public void testPassed(String passFail, String desc) {
		test.pass(MarkupHelper.createLabel("PASSED: " + desc, ExtentColor.GREEN));
	}

	public void testInfo(String passFail, String desc) {
		test.info(MarkupHelper.createLabel("INFO: " + desc, ExtentColor.BLUE));
		// TODO: test = extent.createTest(desc).info(MarkupHelper.createCodeBlock("Add
		// details if needed"));
	}

	public void testFail(String passFail, String desc) throws Exception {
		String imageDesc;


		imageDesc = "TEST_"+ desc;
		if (regression) {
			imageDesc = desc;
			//regressionTestFail(passFail,imageDesc);
		}

		imageDesc = imageDesc.trim();
		imageDesc = imageDesc.replaceAll("\\.", "");
		imageDesc = imageDesc.replaceAll("\\s+", "_");
		//If you need to re-enable screenshot uncomment line 198 and line 35
		//captureScreen.ScreenShot(imageDesc);
		Logs location = new Logs();
		String imageFileName = location.getDirPath() + "DriverDash_" + imageDesc + ".jpeg";
		Logs logs = new Logs();

		try {
			ExtentTest et = test.fail(MarkupHelper.createLabel("FAILED: " + desc, ExtentColor.RED));

			File tempFile = new File(imageFileName);

			if (tempFile.exists()) {
				et.addScreenCaptureFromPath(imageFileName);
			}

		} catch (IOException e) {

			try {
				test.fail(MarkupHelper.createLabel("FAILED: " + desc, ExtentColor.RED))
						.addScreenCaptureFromPath(location.getDirPath() + "DriverDash" + desc + ".jpeg");
			} catch (IOException e1) {

			}

		}

		//logs.CapturedLogs(INFO, "Test continued after prior test failure");
		//logs.setupTest("Test continued after prior test failure");
		//"<font color = yellow>" + "Test continued after prior test failure" + "</font>"
		ExtentTest infoAttempt = test.info(MarkupHelper.createLabel("INFO: " + "<font color = white>" + "Test continued after prior test failure" + "</font>",ExtentColor.GREY));
		//logs.CapturedLogs(INFO,"<font color = black>" + "Test continued after prior test failure" + "</font>" );
		org.testng.Assert.fail("Failure reason: " + desc);

		if (regression){
			regressionTestFail();
		}
	}

	public void regressionTestFail(){
		org.testng.Assert.fail("Failed");
	}

	public String getDirPath() {


		LocalConfiguration logPath = new LocalConfiguration();
		String logpath = null;
		try {
			//logpath =	System.getProperty("user.home");
			//logpath = logPath.getConfigurationValue("extentreport");
			logpath = "./src/testLogs/systemTesting/";
			if (regression) {
				//logpath =	System.getProperty("user.home");
				//logpath = logPath.getConfigurationValue("extentreportRegression");
				logpath = "./src/testLogs/regressionTesting/";

			}
		} catch (Exception e) {
			//logpath = "/Users/W502702/AutomationLogs/logs/AutoLog_HTML_files/";
			logpath = "./src/testLogs/extra/";
		}
		return logpath  + fileDateSTR + "/";
	}

	@AfterTest
	public void tearDowndriver() throws Exception {
		// add statements or other logging you want at the end of the test.
	}

	@AfterSuite
	public void tearDown() {
		// to write or update test information to reporter
		extent.flush();
	}



}
