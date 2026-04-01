package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobject.LoginPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.RecoveryPage;
import org.example.pageobject.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected RecoveryPage recoveryPage;

@Before
public void setUp() {
    String browser = System.getProperty("browser", "chrome");
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    if ("yandex".equals(browser)) options.setBinary("C:/Users/Admin/Desktop/Praktikum/Driver/yandexdriver.exe");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    mainPage = new MainPage(driver);
    loginPage = new LoginPage(driver);
    registerPage = new RegisterPage(driver);
    recoveryPage = new RecoveryPage(driver);
}

@After
public void closeBrowser() {
    if (driver != null) driver.quit(); }
}