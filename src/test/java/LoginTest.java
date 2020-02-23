import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;

public class LoginTest {

    private static String baseURL = "https://accounts.google.com/signup/v2/webcreateaccount?hl=en&flowName=GlifWebSignIn&flowEntry=SignUp";

    @BeforeClass
    public void init(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver");
        RegisterPage.loadPage(baseURL);
    }

    @Test
    public void testLogin() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        RegisterPage.registerNewUser("DelBar888", "Del123", "DelBar12345777", "Abcd1234@");
        Thread.sleep(5000);

        softAssert.assertEquals(RegisterPage.isRegisterSuccess(), true, "Login Success message");
        softAssert.assertAll();
    }
    @AfterClass
    public void cleanUp(){
        RegisterPage.closeBrowser();
    }
}
