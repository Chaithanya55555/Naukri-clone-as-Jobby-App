package org.baseClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.HeaderSection;
import org.pages.HomePage;
import org.pages.JobsPage;
import org.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class baseClassForTestNG {
    public static WebDriver driver;
    public HeaderSection headerSection;
    public LoginPage loginPage;
    public HomePage homePage;
    public JobsPage jobsPage;
    public WebDriverWait wait;
    public static Logger logger;
    public Properties p;

    @Parameters({"br","os"})
    @BeforeMethod
    public void setUp( String br ,  String os) throws IOException {
        FileReader file = new FileReader("C:\\Users\\HP\\IdeaProjects\\JobbyAppTest5\\src\\test\\resources\\configuration.properties");
        p = new Properties();
        p.load(file);
        logger = LogManager.getLogger(this.getClass());
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-background-services",
                "--blink-settings=imagesEnabled=false",
                "--no-sandbox");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--headless");
        System.out.println(br + os);
        if(p.getProperty("execute").equals("remote")){
            DesiredCapabilities cap = new DesiredCapabilities();
            if (os.equalsIgnoreCase("window")) {
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            }else {
                System.out.println("No matching OS");
                return;
            }
            switch (br.toLowerCase()) {
                case "chrome": cap.setBrowserName("chrome");break;
                case "edge": cap.setBrowserName("MicrosoftEdge");break;
                case "firefox": cap.setBrowserName("firefox");break;
                case "safari": cap.setBrowserName("safari");
                default: System.out.println("Invalid browser");return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        }


        if(p.getProperty("execute").equals("local")){
            switch (br.toLowerCase()){
                case "chrome":WebDriverManager.chromedriver().setup();driver = new ChromeDriver(chromeOptions);
                case "edge":WebDriverManager.edgedriver().setup();driver =new EdgeDriver(edgeOptions);
            }
        }


        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);
        homePage = new HomePage(driver);
        jobsPage = new JobsPage(driver);
        driver.get(p.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }

    }

    public String captureScreen(String tName){
        String TimeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

        File soruceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String tarFile = "C:\\Users\\HP\\IdeaProjects\\JobbyAppTest5\\screenshots\\" + tName + " " + TimeStamp + ".png";
        File targetFile = new File(tarFile);
        soruceFile.renameTo(targetFile);
        return tarFile;
    }
}
