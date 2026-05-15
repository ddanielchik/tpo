package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StatPage extends Page {

    private final String footballUrl = baseUrl + "/stat/football/";
    private final String tennisUrl = baseUrl + "/stat/tennis/";
    private final String hockeyUrl = baseUrl + "/stat/hockey/";
    private final String mmaUrl = baseUrl + "/stat/mma/";

    private final By calendarButton =
            By.xpath("//button[contains(@class, 'calendarButton')]");

    private final By calendar =
            By.xpath("//*[@data-qa='Calendar']");

    public StatPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        openFootballDirectly();
    }

    public void openFootballDirectly() {
        driver.get(footballUrl);
        wait.until(driver -> getCurrentUrl().contains("/stat/football"));
    }

    public void openTennisDirectly() {
        driver.get(tennisUrl);
        wait.until(driver -> getCurrentUrl().contains("/stat/tennis"));
    }

    public void openHockeyDirectly() {
        driver.get(hockeyUrl);
        wait.until(driver -> getCurrentUrl().contains("/stat/hockey"));
    }

    public void openMmaDirectly() {
        driver.get(mmaUrl);
        wait.until(driver -> getCurrentUrl().contains("/stat/mma"));
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/stat/");
    }

    public boolean isFootballPage() {
        return getCurrentUrl().contains("/stat/football");
    }

    public boolean isTennisPage() {
        return getCurrentUrl().contains("/stat/tennis");
    }

    public boolean isHockeyPage() {
        return getCurrentUrl().contains("/stat/hockey");
    }

    public boolean isMmaPage() {
        return getCurrentUrl().contains("/stat/mma");
    }

    public boolean hasDaySwitchButtons() {
        return pageContainsText("Вчера")
                && pageContainsText("Сегодня")
                && pageContainsText("Завтра");
    }

    public void clickYesterday() {
        clickDayButton("Вчера");
    }

    public void clickToday() {
        clickDayButton("Сегодня");
    }

    public void clickTomorrow() {
        clickDayButton("Завтра");
    }

    private void clickDayButton(String text) {
        By locator = By.xpath(
                "//*[self::a or self::button][contains(normalize-space(), '" + text + "')]"
        );

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );

        wait.until(driver -> isOpened());
    }

    public void openFootball() {
        openFootballDirectly();
    }

    public void openTennis() {
        openTennisDirectly();
    }

    public void openHockey() {
        openHockeyDirectly();
    }

    public void openMma() {
        openMmaDirectly();
    }

    public void openCalendar() {
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(calendarButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                button
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(calendar));
    }

    public boolean isCalendarOpened() {
        return !driver.findElements(calendar).isEmpty();
    }

    public boolean hasDateInCalendar(String date) {
        return !driver.findElements(By.xpath("//*[@data-date='" + date + "']")).isEmpty();
    }

    public void selectDateInCalendar(String date) {
        By dateLocator = By.xpath("//*[@data-date='" + date + "']");

        WebElement dateElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(dateLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                dateElement
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dateElement
        );

        wait.until(driver -> getCurrentUrl().contains("date=" + date) || isOpened());

        if (!getCurrentUrl().contains("date=" + date)) {
            openCurrentSportWithDate(date);
        }

        wait.until(driver -> getCurrentUrl().contains("date=" + date));
    }

    private void openCurrentSportWithDate(String date) {
        if (isFootballPage()) {
            driver.get(footballUrl + "?date=" + date);
        } else if (isTennisPage()) {
            driver.get(tennisUrl + "?date=" + date);
        } else if (isHockeyPage()) {
            driver.get(hockeyUrl + "?date=" + date);
        } else if (isMmaPage()) {
            driver.get(mmaUrl + "?date=" + date);
        }
    }
}