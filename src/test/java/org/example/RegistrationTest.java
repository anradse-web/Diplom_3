package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.constants.Endpoints;
import org.example.models.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationTest extends BaseTest {
    private UserModel user;


    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка перехода на страницу логина после успешной регистрации")
    public void successRegTest() {
        driver.get(Endpoints.REGISTER);
        registerPage.waitForLoad();
        registerPage.registration("Timothy", "margie_" + System.currentTimeMillis() + "@ya.ru", "password");
        loginPage.waitForLoad();
        Assert.assertTrue("Кнопка входа не активна после регистрации", loginPage.isRegisterButtonEnabled());
    }

    @Test
    @DisplayName("Ошибка: короткий пароль")
    @Description("Проверка появления ошибки при вводе пароля меньше 6 символов")
    public void shortPassRegTest() {
        driver.get(Endpoints.REGISTER);
        registerPage.waitForLoad();

        registerPage.registration("Timothy", "test@ya.ru", "54321");
        Assert.assertTrue(registerPage.wrongPasswordTextIsDisplayed());
    }
}