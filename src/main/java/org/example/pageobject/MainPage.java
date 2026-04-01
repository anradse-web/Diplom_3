package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;



public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Объявляем все локаторы как поля класса
    private final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");
    private final By personalAccount = By.xpath("//a[.//p[text()='Личный Кабинет']]");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By checkoutButton = By.xpath(".//button[contains(text(),'Оформить')]");
    // Добавляем объявление tabElements
    private final By tabElements = By.cssSelector("div.tab_tab");

    public MainPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(constructorHeader));

    }

    @Step("Нажать на кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    @Step("Нажать на кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(personalAccount));
        element.click();
    }

    @Step("Перейти к разделу конструктора {index}")
    public void clickTab(int index) {
        List<WebElement> tabs = driver.findElements(By.cssSelector("div[class*='tab_tab']"));

        if (index < 1 || index > tabs.size()) {
            throw new IllegalArgumentException("Некорректный индекс вкладки: " + index);
        }

        tabs.get(index - 1).click();
    }
    @Step("Получить количество вкладок")
    public int getTabsCount() {
        List<WebElement> tabs = driver.findElements(By.cssSelector("div[class*='tab_tab']"));
        return tabs.size();
    }

    @Step("Проверить активность таба {index}")
    public boolean isTabActive(int index) {
        List<WebElement> tabs = driver.findElements(By.cssSelector("div[class*='tab_tab']"));

        if (index < 1 || index > tabs.size()) return false;

        String classes = tabs.get(index - 1).getAttribute("class");
        return classes.contains("tab_tab_type_current");
    }

    @Step("Проверить авторизацию")
    public boolean isUserLoggedIn() {
        try { return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}