package com.amity.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.List;
import com.amity.app.utils.CommonUtils;

public class PimPage {
    private WebDriver driver;
    private By pimMenu = By.cssSelector("a[href='/web/index.php/pim/viewPimModule']");
    private By empNameForm = By.xpath("//label[text()='Employee Name']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']/descendant::input");
    private By suggestedElementContainer = By.cssSelector("div[role='listbox']");
    private By nameContainer = By.xpath("//div[@role='listbox']");
    private By nameRows = By.xpath(".//div/span");
    private By searchBtn = By.xpath("//button[@type='submit']"); 
    private By empTable = By.cssSelector("div.oxd-table.orangehrm-employee-list");
    private By empTableRows = By.xpath(".//div[@class='oxd-table-card']");
    private By firstNameLocator = By.xpath(".//div[@class='oxd-table-row']/div[@class='oxd-table-cell oxd-padding-cell'][3]");
    private By lastNameLocator = By.xpath(".//div[@class='oxd-table-row']/div[@class='oxd-table-cell oxd-padding-cell'][4]");

    public PimPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPimMenu() {
        CommonUtils.waitForElementToBeVisible(this.driver, pimMenu, 120);
    }

    public void clickPimMenu() {
        driver.findElement(pimMenu).click();
    }

    public void waitForEmpNameForm() {
        CommonUtils.waitForElementToBeVisible(this.driver, empNameForm, 120);
    }
  
    public void enterEmpName(String empName) {
        driver.findElement(empNameForm).sendKeys(empName);
    } 

    public void waitForAutoSuggestionList() {
        CommonUtils.waitForElementToBeVisible(this.driver, suggestedElementContainer, 120);
    }

    public List<WebElement> getNameRows() {
        WebElement container = driver.findElement(nameContainer);
        return container.findElements(nameRows);
    } 

    public void verifyNameContainCh(List<WebElement> rows) {
        for(WebElement name: rows) {
            String str= name.getText();
            Assert.assertTrue(str.toLowerCase().contains("ch"));
        }
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public void scrollIntoView() {
        CommonUtils.scrollIntoView(this.driver, empTable);
    }

    public List<WebElement> getEmpTableRows() {
        WebElement table = driver.findElement(empTable);
        return table.findElements(empTableRows);
    }

    public void verifyElementsContainCh(List<WebElement> rows) {
        int count = 0;
        for(WebElement row: rows) {
            WebElement firstNameCell = row.findElement(firstNameLocator);
            WebElement lastNameCell = row.findElement(lastNameLocator);
            String firstName = firstNameCell.getText();
            String lastName = lastNameCell.getText();
            Assert.assertTrue(firstName.contains("ch") || lastName.contains("ch"), "At least the first 3 should have ch in either First or Last name column");
            count++;
            if(count >= 3) {
                break;
            }
        }
    }
}   