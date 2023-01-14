package com.amity.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.amity.app.utils.CommonUtils;
import java.util.List;
import org.testng.Assert;

public class AdminPage {
    private WebDriver driver;
    private String name;
    private String minimumSalary;
    private String maximumSalary;
    private By adminMenu = By.cssSelector("a[href='/web/index.php/admin/viewAdminModule']");
    private By jobMenu = By.xpath("//span[contains(text(), 'Job')]");
    private By payGradesMenu = By.xpath("//a[text()='Pay Grades']");
    private By addButton = By.xpath("//button[contains(., 'Add')]");
    private By nameForm = By.cssSelector(".oxd-form-row .oxd-input.oxd-input--active");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By selectBox = By.className("oxd-select-wrapper");
    private By minSalary = By.xpath("//label[text()='Minimum Salary']/ancestor::div[@class='oxd-input-group__label-wrapper']/following-sibling::div//input");
    private By maxSalary = By.xpath("//label[text()='Maximum Salary']/ancestor::div[@class='oxd-input-group__label-wrapper']/following-sibling::div//input");
    private By saveBtn = By.xpath("//h6[text()='Add Currency']/ancestor::div/form/div/button[@type='submit']");
    private By currenciesTitle = By.xpath("//h6[text()='Currencies']");
    private By currenciesTable = By.cssSelector("div.oxd-table");
    private By currenciesTableRows = By.xpath(".//div[@class='oxd-table-card']");
    private By currencyCell = By.xpath(".//div[@class='oxd-table-row']/div[@class='oxd-table-cell oxd-padding-cell'][2]");
    private By minSalaryCell = By.xpath(".//div[@class='oxd-table-row']/div[@class='oxd-table-cell oxd-padding-cell'][3]");
    private By maxSalaryCell = By.xpath(".//div[@class='oxd-table-row']/div[@class='oxd-table-cell oxd-padding-cell'][4]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAdminMenu() {
        CommonUtils.waitForElementToBeVisible(this.driver, adminMenu, 120);
    }

    public void clickAdminMenu() {
        driver.findElement(adminMenu).click();
    }

    public void waitForJobMenu() {
        CommonUtils.waitForElementToBeVisible(this.driver, jobMenu, 120);
    }

    public void clickJobMenu() {
        driver.findElement(jobMenu).click();
    }

    public void clickPayGradesMenu() {
        driver.findElement(payGradesMenu).click();
    }

    public void waitForAddButton() {
        CommonUtils.waitForElementToBeVisible(this.driver, addButton, 120);
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void waitForNameForm() {
        CommonUtils.waitForElementToBeVisible(this.driver, nameForm, 120);
    }

    public void enterNameForm(String name) {
        this.name = name;
        driver.findElement(nameForm).sendKeys(name);
    } 

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public void waitForSelectBox() {
        CommonUtils.waitForElementToBeVisible(this.driver, selectBox, 120);
    }

    public void clickSelectBox() {
        driver.findElement(selectBox).click();
    }

    public void selectCurrency(String value) {
        By selectCurrencyElement = By.xpath("//div[@role='listbox']//span[text()='"+value+"']");
        CommonUtils.waitForElementToBeVisible(this.driver, selectCurrencyElement, 120);
        driver.findElement(selectCurrencyElement).click();
    }

    public void enterMinimumSalary(int salary) {
        this.minimumSalary = Integer.toString(salary);
        driver.findElement(minSalary).sendKeys(this.minimumSalary);
    }

    public void enterMaximumSalary(int salary) {
        this.maximumSalary = Integer.toString(salary);
        driver.findElement(maxSalary).sendKeys(this.maximumSalary);
    }

    public void clickSaveBtn() {
        driver.findElement(saveBtn).click();
    }

    public void waitForCurrenciesTitle() {
        CommonUtils.waitForElementToBeVisible(this.driver, currenciesTitle, 120);
    }

    public List<WebElement> getCurrenciesTableRows() {
        WebElement currenciestable = driver.findElement(currenciesTable);
        return currenciestable.findElements(currenciesTableRows);
    }

    public void verifyRecords(List<WebElement> rows) {
        for(WebElement row: rows) {
            WebElement currencycell = row.findElement(currencyCell);
            if(currencycell.getText().equals(this.name)) {
                WebElement minsalary = row.findElement(minSalaryCell);
                Assert.assertTrue(minsalary.getText().matches(this.minimumSalary), "Minimum Salary is not correct");

                WebElement maxsalary = row.findElement(maxSalaryCell);
                Assert.assertTrue(maxsalary.getText().matches(this.maximumSalary), "Maximum Salary is not correct");
                break;
            }
        }
    }
 }
