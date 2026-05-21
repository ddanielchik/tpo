package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private final String bookmakersLink =
            "//a[@href='/bukmekery/' and contains(normalize-space(.), 'Букмекеры')]";

    private final String matchCenterLink =
            "//a[@href='/stat/football/' and contains(normalize-space(.), 'Матч-центр')]";

    private final String bonusesLink =
            "//a[@href='/bonusy/' and contains(normalize-space(.), 'Бонусы')]";

    private final String magazineLink =
            "//a[@href='/mag/' and contains(normalize-space(.), 'Журнал')]";

    private final String forecastsLink =
            "//a[@href='/prognozy/' and contains(normalize-space(.), 'Прогнозы')]";

    private final String knowledgeBaseLink =
            "//a[@href='/enciklopediya/' and contains(normalize-space(.), 'База знаний')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/");
    }

    public boolean hasHeaderNavigation() {
        return isElementPresent(bookmakersLink)
                && isElementPresent(matchCenterLink)
                && isElementPresent(bonusesLink)
                && isElementPresent(magazineLink)
                && isElementPresent(forecastsLink)
                && isElementPresent(knowledgeBaseLink);
    }

    public void openBookmakers() {
        clickByXpath(bookmakersLink);
    }

    public void openBonuses() {
        clickByXpath(bonusesLink);
    }

    public void openMatchCenter() {
        clickByXpath(matchCenterLink);
    }

    public void openForecasts() {
        clickByXpath(forecastsLink);
    }

    public void openMagazine() {
        clickByXpath(magazineLink);
    }

    public void openKnowledgeBase() {
        clickByXpath(knowledgeBaseLink);
    }
}