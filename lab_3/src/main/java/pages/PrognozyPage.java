package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PrognozyPage extends Page {

    private final String forecastsTitle =
            "//*[contains(normalize-space(.), 'Прогнозы на спорт')]";

    private final String forecastCards =
            "//a[contains(@href, '/prognozy/') and not(@href='/prognozy/')]";

    private final String footballForecasts =
            "//a[contains(@href, '/prognozy/football/') or contains(normalize-space(.), 'Футбол')]";

    private final String tennisForecasts =
            "//a[contains(@href, '/prognozy/tennis/') or contains(normalize-space(.), 'Теннис')]";

    private final String hockeyForecasts =
            "//a[contains(@href, '/prognozy/hockey/') or contains(normalize-space(.), 'Хоккей')]";

    private final String forecastTextInfo =
            "//*[contains(normalize-space(.), 'Прогноз')]";

    public PrognozyPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/prognozy/");
    }

    public boolean hasForecastsPageContent() {
        return hasForecastsPageHeader()
                && hasForecastCards();
    }

    public boolean hasForecastsPageHeader() {
        return isElementPresent(forecastsTitle);
    }

    public boolean hasForecastCards() {
        return isElementPresent(forecastCards)
                || isElementPresent(forecastTextInfo);
    }

    public boolean hasFootballForecastsLink() {
        return isElementPresent(footballForecasts);
    }

    public boolean hasTennisForecastsLink() {
        return isElementPresent(tennisForecasts);
    }

    public boolean hasHockeyForecastsLink() {
        return isElementPresent(hockeyForecasts);
    }

    public void openFootballForecasts() {
        if (!hasFootballForecastsLink()) {
            throw new IllegalStateException("Ссылка на футбольные прогнозы не найдена");
        }

        jsClickByXpath(footballForecasts);
    }

    public void openTennisForecasts() {
        if (!hasTennisForecastsLink()) {
            throw new IllegalStateException("Ссылка на теннисные прогнозы не найдена");
        }

        jsClickByXpath(tennisForecasts);
    }

    public void openHockeyForecasts() {
        if (!hasHockeyForecastsLink()) {
            throw new IllegalStateException("Ссылка на хоккейные прогнозы не найдена");
        }

        jsClickByXpath(hockeyForecasts);
    }

    public boolean hasSportForecasts(String sportName) {
        return isTextPresent(sportName)
                && hasForecastCards();
    }

    public void openFirstForecast() {
        List<WebElement> forecasts = findElementsByXpath(forecastCards);

        if (forecasts.isEmpty()) {
            throw new IllegalStateException("Не найдены карточки прогнозов");
        }

        scrollTo(forecasts.get(0));
        forecasts.get(0).click();
    }
}