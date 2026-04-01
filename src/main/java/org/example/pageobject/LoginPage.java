package org.example.pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginHeader = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath("//input[@name='name' or @type='text']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton =  By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Ожидание загрузки страницы логина")
    public void waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }
    @Step("Авторизация вводим email, password")
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }
    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка активности кнопки «Войти»")
    public boolean isRegisterButtonEnabled() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}