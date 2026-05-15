package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BonusyPage extends Page {

    private final By bookmakerFilter = By.xpath(
            "//span[@data-qa='Tag' and contains(., 'Букмекерская контора')]"
    );

    private final By bookmakerSearchInput = By.xpath("//input[@type='text']");

    public BonusyPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/bonusy/";
        driver.get(url);
        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/bonusy/")
                || pageContainsText("Бонусы букмекерских контор");
    }

    public boolean hasBonusCards() {
        String bonusCardsXpath = "//a[contains(@href, '/bonusy/')]";
        return countByXpath(bonusCardsXpath) > 0;
    }

    public void openBookmakerFilter() {
        WebElement filter = wait.until(
                ExpectedConditions.presenceOfElementLocated(bookmakerFilter)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                filter
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                filter
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(bookmakerSearchInput));
    }

    public boolean isBookmakerFilterOpened() {
        return !driver.findElements(bookmakerSearchInput).isEmpty()
                && pageContainsText("Поиск по букмекерам");
    }

    public void searchBookmaker(String text) {
        openBookmakerFilter();
        searchBookmakerWithoutOpeningFilter(text);
    }

    public void searchBookmakerWithoutOpeningFilter(String text) {
        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(bookmakerSearchInput)
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

        wait.until(driver ->
                text.equals(input.getAttribute("value"))
        );
    }

    public void selectBookmaker(String bookmakerName) {
        searchBookmaker(bookmakerName);

        WebElement bookmaker = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'" + bookmakerName + "')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                bookmaker
        );

        wait.until(driver -> isOpened());
    }

    public boolean isBookmakerFilterApplied() {
        return hasBonusCards();
    }

    public void selectNewPlayersFilter() {
        clickBonusFilterByText("Новым игрокам");
    }

    public void selectActivePlayersFilter() {
        clickBonusFilterByText("Действующим игрокам");
    }

    private void clickBonusFilterByText(String text) {
        By filter = By.xpath(
                "//span[@data-qa='Tag' and contains(., '" + text + "')]"
        );

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(filter)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}