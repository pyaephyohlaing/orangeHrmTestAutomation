package com.amity.app.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import com.amity.app.pages.LoginPage;
import com.amity.app.pages.AdminPage;
import com.amity.app.utils.CommonUtils;
import java.util.List;

public class VerifyUserCanAddPayGrades {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;

    @BeforeTest
    public void setUp() {
        CommonUtils.setUpSeleniumWebDriver();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123");   
    }
    
    @Test
    public void testEmployeeSearch() throws InterruptedException {
        adminPage = new AdminPage(driver);
        adminPage.waitForAdminMenu();
        adminPage.clickAdminMenu(); 
        adminPage.waitForJobMenu();
        adminPage.clickJobMenu();
        Thread.sleep(3000);
        adminPage.clickPayGradesMenu();
        Thread.sleep(3000);
        adminPage.waitForAddButton();
        adminPage.clickAddButton();
        Thread.sleep(3000);
        adminPage.waitForNameForm();
        adminPage.enterNameForm("Indian Rupee");
        adminPage.clickSubmitButton();
        Thread.sleep(3000);
        adminPage.waitForAddButton();
        adminPage.clickAddButton();
        Thread.sleep(3000);
        adminPage.waitForSelectBox();
        adminPage.clickSelectBox();
        adminPage.selectCurrency("INR - Indian Rupee");
        adminPage.enterMinimumSalary(30000);
        adminPage.enterMaximumSalary(100000);
        adminPage.clickSaveBtn();
        Thread.sleep(3000);
        adminPage.waitForCurrenciesTitle();
        List<WebElement> rows = adminPage.getCurrenciesTableRows();
        adminPage.verifyRecords(rows);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
