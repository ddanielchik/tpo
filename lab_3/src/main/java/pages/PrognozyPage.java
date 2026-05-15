package pages;

import org.openqa.selenium.WebDriver;

public class PrognozyPage extends Page {

    private final String forecastCardsXpath =
            "//a[contains(@href, '/prognozy/') and .//span[@data-atr='forecast']]";

    public PrognozyPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/prognozy/";
        driver.get(url);
        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/prognozy/");
    }

    public boolean hasHeader() {
        String pageHeaderXpath = "//*[contains(normalize-space(), 'Прогноз')]";
        return isElementPresent(pageHeaderXpath);
    }

    public boolean hasForecastCards() {
        return getForecastCardsCount() > 0;
    }

    public int getForecastCardsCount() {
        return countByXpath(forecastCardsXpath);
    }

    public boolean hasSportFilter() {
        String footballFilterXpath = "//*[contains(normalize-space(), 'Футбол')]";
        return isElementPresent(footballFilterXpath);
    }

    public void selectSportFilter(String sport) {
        clickByXpath("//*[contains(normalize-space(), '" + sport + "')]");
        wait.until(driver -> isOpened());
    }

    public void openFirstForecast() {
        String oldUrl = getCurrentUrl();

        jsClickByXpath(forecastCardsXpath);

        wait.until(driver -> !getCurrentUrl().equals(oldUrl)
                || pageContainsText("Сергей Шевченко")
                || pageContainsText("Подробнее"));
    }

    public boolean hasForecastMaterialTitle() {
        String materialTitleXpath = "//h1 | //h2 | //h3";
        return isElementPresent(materialTitleXpath)
                || pageContainsText("матч");
    }
}