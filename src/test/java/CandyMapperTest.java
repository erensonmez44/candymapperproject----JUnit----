import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CandyMapperTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://candymapper.com/");

        System.out.println("Page title : " +driver.getTitle());
        String actualTitle = driver.getTitle();
        String expectedTitle = "CandyMapper.Com";
        if (actualTitle.contains(expectedTitle)){
            System.out.println("Title Test passed");
        }else {
            System.out.println("Title test failed");
        }

        WebElement closePopup = driver.findElement(By.id("popup-widget163045-close-icon"));
        closePopup.click();
        Thread.sleep(1000);
        WebElement joinUs= driver.findElement(By.xpath("//a[@data-page=\"c9e65620-4aec-44b7-bf7e-90a2cb36a1bf\"][1]"));
        joinUs.click();
        Thread.sleep(1000);
        WebElement createAccount = driver.findElement(By.xpath("//a[normalize-space()='Create account.']"));
        createAccount.click();
        Thread.sleep(1000);
        WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='First name']"));
        firstName.sendKeys("Eren");
        Thread.sleep(1000);
        WebElement lastName= driver.findElement(By.xpath("//input[@placeholder='Last name']"));
        lastName.sendKeys("Sonmez");
        Thread.sleep(1000);
        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        email.sendKeys("kireja7294@sentrau.com");
        Thread.sleep(1000);
        WebElement phone = driver.findElement(By.xpath("//input[@placeholder='Phone (optional)']"));
        phone.sendKeys("05456651111");
        Thread.sleep(1000);
        WebElement lastCreateAccount = driver.findElement(By.xpath("//button[normalize-space()='Create Account']"));
        lastCreateAccount.click();
        Thread.sleep(3000);

        WebElement accountCreatedMessage = driver.findElement(By.xpath("//h4[@aria-level=\"4\"]"));
        if(accountCreatedMessage.isDisplayed()){
            System.out.println("Check your email address and Activate account");
        }else {
            System.out.println("Have a problem!!! Create an account again");
        }
        Thread.sleep(1000);

        driver.get("https://candymapper.com/m/login?r=%2Fm%2Faccount");

        WebElement logEmail = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        logEmail.sendKeys("kireja7294@sentrau.com");
        Thread.sleep(1000);
        WebElement logPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        logPassword.sendKeys("123456789Ee");
        Thread.sleep(1000);
        WebElement signIn = driver.findElement(By.xpath("//button[@data-ux='ButtonPrimary']"));
        signIn.click();
        driver.get("https://candymapper.com/m/account?alert=ACCOUNT_CREATED_SUCCESSFULLY");

        TakesScreenshot tss = (TakesScreenshot) driver;
        String output = System.getProperty("user.dir") +"//Screenshot//fullPage.png";
        File fullPageSS = new File(output);
        Thread.sleep(2000);

        File temporarySS = tss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temporarySS,fullPageSS);

        WebElement accountCreatedMessage1 = driver.findElement(By.xpath("//div[@data-aid=\"ALERT\"]"));
        if (accountCreatedMessage1.isDisplayed()){
            System.out.println("Account created successfully.");
        }else{
            System.out.println("Account could not be created");
        }
        Thread.sleep(1000);
        driver.close();
    }
}
