package com.phptravels.pages;

import com.phptravels.config.ConfigurationManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class HomePage extends LoadableComponent<HomePage> {
    private final WebDriver driver;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/clientarea.php";

    @FindBy(xpath = "//a[normalize-space()='Home']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[normalize-space()='Update']")
    private WebElement updateButton;



    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public boolean isCorrectlyDisplayed() {
        boolean isUpdateInfoDisplayed = updateButton.isDisplayed();
        boolean isHomeLinkDisplayed = homeLink.isDisplayed();
        return isUpdateInfoDisplayed && isHomeLinkDisplayed;
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
        pageActions.waitPersistentlyForElementToAppear(updateButton,5);
        assertTrue(isCorrectlyDisplayed());
    }
}
