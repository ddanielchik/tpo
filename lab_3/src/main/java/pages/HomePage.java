package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    private final String mainContentXpath =
            "//*[contains(normalize-space(), 'Легальные букмекеры') or " +
                    "contains(normalize-space(), 'Статистика матчей') or " +
                    "contains(normalize-space(), 'Матчи по футболу') or " +
                    "contains(normalize-space(), 'Букмекеры')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/";
        driver.get(url);
        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("sravni.bet")
                && isElementPresent(mainContentXpath);
    }

    public boolean hasMainNavigation() {
        return hasCommonNavigation();
    }

    public boolean hasMainContent() {
        return isElementPresent(mainContentXpath);
    }

    public void openBonusy() {
        String bonusyLinkXpath = "//a[contains(normalize-space(), 'Бонусы')]";
        clickByXpath(bonusyLinkXpath);
        waitUntilUrlContains("/bonusy/");
    }
}