package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By loginLink = By.xpath(".//a[contains(text(), 'Войти')]");

    public RecoveryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Переход по ссылке 'Войти' со страницы восстановления пароля")
    public void clickLoginLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        element.click();
    }
}

