package com.amity.app.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import com.amity.app.pages.LoginPage;
import com.amity.app.pages.PimPage;
import com.amity.app.utils.CommonUtils;
import java.util.List;

public class EmployeeSearchTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private PimPage pimPage;

    @BeforeTest
    public void setUp() {
        CommonUtils.setUpSeleniumWebDriver();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123");   
    }
    
    @Test
    public void testEmployeeSearch() throws InterruptedException {
        pimPage = new PimPage(driver);
        pimPage.waitForPimMenu();
        pimPage.clickPimMenu();
        pimPage.waitForEmpNameForm();
        pimPage.enterEmpName("ch");
        pimPage.waitForAutoSuggestionList();
        List<WebElement> rows = pimPage.getNameRows();
        pimPage.verifyNameContainCh(rows);
        Thread.sleep(3000);
        pimPage.clickSearch();
        pimPage.scrollIntoView();
        List<WebElement> row = pimPage.getEmpTableRows();
        pimPage.verifyElementsContainCh(row);
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}