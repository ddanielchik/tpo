package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class Page {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String baseUrl = "https://sravni.bet";

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void openPath(String path) {
        driver.get(baseUrl + path);
    }

    protected WebElement findByXpath(String xpath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement findVisibleByXpath(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected List<WebElement> findElementsByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    protected void clickByXpath(String xpath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        scrollTo(element);
        element.click();
    }

    protected void jsClickByXpath(String xpath) {
        WebElement element = findByXpath(xpath);
        scrollTo(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    protected boolean isElementPresent(String xpath) {
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    protected boolean isTextPresent(String text) {
        return driver.findElement(By.tagName("body"))
                .getText()
                .toLowerCase()
                .contains(text.toLowerCase());
    }

    protected void checkElementPresent(String xpath, String message) {
        Assertions.assertTrue(isElementPresent(xpath), message);
    }

    protected void checkTextPresent(String text, String message) {
        Assertions.assertTrue(isTextPresent(text), message);
    }

    protected void checkAnyElementPresent(String message, String... xpaths) {
        for (String xpath : xpaths) {
            if (isElementPresent(xpath)) {
                return;
            }
        }

        Assertions.fail(message);
    }

    protected String linkByText(String text) {
        return "//a[contains(normalize-space(.), '" + text + "')]";
    }

    protected String linkByHrefAndText(String href, String text) {
        return "//a[contains(@href, '" + href + "') and contains(normalize-space(.), '" + text + "')]";
    }
}