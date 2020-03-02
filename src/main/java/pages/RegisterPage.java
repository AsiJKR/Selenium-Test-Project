package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterPage {

    public static WebDriver driver = new ChromeDriver();

    static By firstname = By.xpath("//input[@id='firstName']");
    static By lastname = By.xpath("//input[@id='lastName']");
    static By username = By.xpath("//input[@id='username']");
    static By password = By.xpath("//input[@name='Passwd']");
    static By confpassword = By.xpath("//input[@name='ConfirmPasswd']");
    static By submit = By.xpath("//span[text()='Next']");

    static By successMsg = By.xpath("//h1[text()='Verify your phone number']");

    public static void loadPage(String baseURL){
        driver.get(baseURL);
    }

    public static void registerNewUser(String fname, String lname, String uname, String pass){

        driver.findElement(firstname).sendKeys(fname);
        driver.findElement(lastname).sendKeys(lname);
        driver.findElement(username).sendKeys(uname);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confpassword).sendKeys(pass);
        driver.findElement(submit).click();
    }

    public static boolean isRegisterSuccess(){
       return driver.findElement(successMsg).isDisplayed();
    }

    public static void closeBrowser(){
        driver.close();
    }
}
