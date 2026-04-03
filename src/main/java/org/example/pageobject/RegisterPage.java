package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath("//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input[contains(@class, 'input__textfield')]");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By registrationHeader = By.xpath(".//h2[text()='Регистрация']");
    private final By registerButton = By.xpath(".//button[contains(@class, 'button_button_type_primary')]");
    private final By wrongPasswordText = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Ожидание загрузки страницы регистрации")
    public void waitForLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
    }
    @Step("Нажатие на кнопку Войти")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Регистрация: {email}, {name}, {password}")
    public void registration(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }

    @Step("Проверить ошибку пароля")
    public boolean wrongPasswordTextIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordText)).isDisplayed();
    }
    @Step("Проверка загрузки страницы логина")
    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
