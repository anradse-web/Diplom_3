package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.constants.Endpoints;
import org.example.pageobject.MainPage;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest extends BaseTest {

    @DisplayName("Проверка переключения на раздел 'Соусы'")
    @Description("Убедиться, что вкладка 'Соусы' (2‑я) становится активной после клика")
    @Test
    public void switchToSauceTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(2);
        Assert.assertTrue(mainPage.isTabActive(2));
    }
    @DisplayName("Проверка переключения на раздел 'Начинки'")
    @Description("Убедиться, что вкладка 'Начинки' (3‑я) становится активной после клика")
    @Test

    public void switchToFillingTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(3);
        Assert.assertTrue(mainPage.isTabActive(3));
    }

    @DisplayName("Проверка переключения на раздел 'Булки'")
    @Description("Убедиться, что вкладка 'Булки' (1‑я) становится активной после клика")
    @Test
    public void switchToBunTest() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickTab(2);
        mainPage.clickTab(1);
        Assert.assertTrue(mainPage.isTabActive(1));
    }
}