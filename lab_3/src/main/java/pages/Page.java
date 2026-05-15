package pages;

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

    protected List<WebElement> findAllByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    protected void clickByXpath(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    protected boolean isElementPresent(String xpath) {
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public boolean pageContainsText(String text) {
        return driver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }

    protected void openUrl(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        driver.get(baseUrl + path);
    }

    protected int countByXpath(String xpath) {
        return findAllByXpath(xpath).size();
    }

    protected void waitUntilUrlContains(String text) {
        wait.until(driver -> getCurrentUrl().contains(text));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void openBukmekeryPage() {
        openUrl("/bukmekery/");
        waitUntilUrlContains("/bukmekery/");
    }


    public void openPrognozyPage() {
        openUrl("/prognozy/");
        waitUntilUrlContains("/prognozy/");
    }

    public void openEnciklopediyaPage() {
        openUrl("/enciklopediya/");
        waitUntilUrlContains("/enciklopediya/");
    }

    public void openMagPage() {
        openUrl("/mag/");
        waitUntilUrlContains("/mag/");
    }

    public void openStatFootballPage() {
        openUrl("/stat/football/");
        waitUntilUrlContains("/stat/football/");
    }

    public boolean hasCommonNavigation() {
        return isElementPresent("//a[contains(normalize-space(), 'Букмекеры')]")
                && isElementPresent("//a[contains(normalize-space(), 'Матч-центр')]")
                && isElementPresent("//a[contains(normalize-space(), 'Бонусы')]")
                && isElementPresent("//a[contains(normalize-space(), 'Журнал')]")
                && isElementPresent("//a[contains(normalize-space(), 'Прогнозы')]")
                && isElementPresent("//a[contains(normalize-space(), 'База знаний')]");
    }

    public abstract void open();

    public abstract boolean isOpened();

    protected void jsClickByXpath(String xpath) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected WebElement findVisibleByXpath(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));

        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }

        throw new NoSuchElementException("Не найден видимый элемент по XPath: " + xpath);
    }

    protected void jsClickVisibleByXpath(String xpath) {
        WebElement element = findVisibleByXpath(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}