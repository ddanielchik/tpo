package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EnciklopediyaPage extends Page {

    private final By searchInput = By.xpath(
            "//div[@data-qa='TextInput']//input[@type='text']"
    );

    private final By searchButton = By.xpath(
            "//div[contains(@class, 'search')]//button[@data-qa='Button']"
    );

    public EnciklopediyaPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/enciklopediya/";
        driver.get(url);

        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/journal/")
                || pageContainsText("База знаний")
                || pageContainsText("Что вы хотите найти");
    }

    public void search(String text) {
        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(searchInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                input
        );

        input.click();

        new Actions(driver)
                .keyDown(Keys.COMMAND)
                .sendKeys("a")
                .keyUp(Keys.COMMAND)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(text)
                .perform();

        String value = input.getAttribute("value");

        if (!text.equals(value)) {
            ((JavascriptExecutor) driver).executeScript("""
                    arguments[0].focus();
                    arguments[0].value = arguments[1];
                    arguments[0].dispatchEvent(new Event('input', { bubbles: true }));
                    arguments[0].dispatchEvent(new Event('change', { bubbles: true }));
                    """, input, text);
        }

        wait.until(driver ->
                text.equals(input.getAttribute("value"))
        );

        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(searchButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );

        wait.until(driver ->
                driver.getCurrentUrl().contains("search")
                        || driver.findElement(By.tagName("body"))
                        .getText()
                        .toLowerCase()
                        .contains(text.toLowerCase())
        );
    }

    public String getSearchInputValue() {
        return driver.findElement(searchInput)
                .getAttribute("value");
    }

    public boolean hasSearchResults(String text) {
        return driver.findElement(By.tagName("body"))
                .getText()
                .toLowerCase()
                .contains(text.toLowerCase());
    }
}