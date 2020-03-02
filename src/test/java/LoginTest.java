import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

import java.io.File;
import java.io.IOException;

public class LoginTest {

    static ExtentTest test;
    static ExtentReports report = new ExtentReports("ExtentReportResults.html");
    private static String baseURL = "https://accounts.google.com/signup/v2/webcreateaccount?hl=en&flowName=GlifWebSignIn&flowEntry=SignUp";

    @BeforeClass
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver");
        RegisterPage.loadPage(baseURL);
        test = report.startTest("ExtentDemo");
    }

    @Test
    public void testLogin() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        RegisterPage.registerNewUser("DelBar888!@", "Del123", "DelBar12345707", "Abcd1234@");
        Thread.sleep(5000);

//        softAssert.assertEquals(RegisterPage.isRegisterSuccess(), true, "Login Success message");
        if (RegisterPage.driver.getTitle().equalsIgnoreCase("hhh")){
            test.log(LogStatus.PASS,"Success message displayed");
        }
        else {
            try {
                test.log(LogStatus.FAIL,test.addScreenCapture(capture(RegisterPage.driver))+"Test Failed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        softAssert.assertAll();
    }
    @AfterClass
    public void cleanUp(){
        RegisterPage.closeBrowser();
        report.endTest(test);
        report.flush();
    }

    public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../screenshots/" + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        Files.copy(scrFile, Dest);
        return errflpath;
    }
}
