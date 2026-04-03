package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.clients.UserClient;
import org.example.constants.Endpoints;
import org.example.models.UserModel;
import org.example.pageobject.LoginPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.RecoveryPage;
import org.example.pageobject.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest {

    private UserModel userModel;
    private String token;
    private UserClient userClient;

    @Before
    public void prepareTestUser() {
        userClient = new UserClient();
        userModel = new UserModel("test1_" + System.currentTimeMillis() + "@yandex.ru", "pass1234", "Anna");
        var response = userClient.createUser(userModel);
        token = response.path("accessToken");

    }

    @After
    public void tearDown() {
        if (token != null) {
            userClient.deleteUser(token);
        }
    }
    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginFromMainPage() {
        driver.get(Endpoints.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userModel.getEmail(), userModel.getPassword());
        loginPage.clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.isUserLoggedIn());
    }


    @DisplayName("Вход через кнопку Личный кабинет")
    @Test
    public void loginFromRegisterButton() {
        driver.get(Endpoints.BASE_URL);
        mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        mainPage.clickPersonalAccountButton();

        loginPage = new LoginPage(driver);
        loginPage.login(userModel.getEmail(), userModel.getPassword());
        loginPage.clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.isUserLoggedIn());
    }

    @DisplayName("Вход через кнопку в форме регистрации")
    @Test
    public void loginFromAccountButton() {
        driver.get(Endpoints.REGISTER);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoad();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userModel.getEmail(), userModel.getPassword());
        loginPage.clickLoginButton();

        Assert.assertTrue("Вход не выполнен", mainPage.isUserLoggedIn());
    }

    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Test
    public void loginFromRecoveryPage() {

        driver.get(Endpoints.FORGOT_PASSWORD);
        recoveryPage = new RecoveryPage(driver);
        recoveryPage.clickLoginLink();

        loginPage.login(userModel.getEmail(), userModel.getPassword());
        loginPage.clickLoginButton();

        Assert.assertTrue("Вход не выполнен", new MainPage(driver).isUserLoggedIn());
    }
}