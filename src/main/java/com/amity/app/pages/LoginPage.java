package com.amity.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.amity.app.utils.CommonUtils;

public class LoginPage {
    private WebDriver driver;
    private static final String LoginPage_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private By userName = By.xpath("//input[@class='oxd-input oxd-input--focus'][@name='username']");
    private By password = By.xpath("//input[@class='oxd-input oxd-input--active'][@name='password']");
    private By loginBtn = By.className("oxd-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        this.driver.get(LoginPage_URL);
    }

    public void waitForEnterUsername() {
        CommonUtils.waitForElementToBeVisible(this.driver, userName, 120);
    }

    public void enterUsername(String username) {
        this.driver.findElement(userName).sendKeys(username);
    }

    public void enterPassword(String pwd) {
        this.driver.findElement(password).sendKeys(pwd);
    }

    public void clickLogin() {
        this.driver.findElement(loginBtn).click();
    }

    public void login(String username, String password) {
        CommonUtils.maximizeWindow(driver);
        openLoginPage();
        waitForEnterUsername();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

