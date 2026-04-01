package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.className("AppHeader_header__linkText__3q_va");
    private final By profileText = By.xpath(".//p[contains(@class, 'Account_text')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Ожидаем загрузку страницы профиля")
    public void waitForProfilePageToLoad () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileText));
    }
    @Step("Нажимаем кнопку 'Выход'")
    public void clickLogout () {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Переход по кнопке 'Конструктор'")
    public void clickConstructor () {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
    }

    @Step("Кликаем по логотипу 'Stellar Burgers'")
    public void clickLogo () {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    @Step("Проверить видимость данных профиля")
    public boolean isProfileInfoVisible () {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(profileText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
