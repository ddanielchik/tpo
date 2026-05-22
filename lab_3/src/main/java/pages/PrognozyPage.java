package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PrognozyPage extends Page {

    private final String forecastsTitle =
            "//h1[contains(normalize-space(.), 'Прогнозы на спорт')]"
                    + " | //*[contains(normalize-space(.), 'Прогнозы на спорт')]";

    private final String footballCategory =
            "//a[contains(@href, '/prognozy/football/') "
                    + "and contains(normalize-space(.), 'Футбол')]";

    private final String tennisCategory =
            "//a[contains(@href, '/prognozy/tennis/') "
                    + "and contains(normalize-space(.), 'Теннис')]";

    private final String hockeyCategory =
            "//a[contains(@href, '/prognozy/hockey/') "
                    + "and contains(normalize-space(.), 'Хоккей')]";

    private final String forecastCards =
            "//a[contains(@href, '/prognozy/') "
                    + "and not(@href='/prognozy/') "
                    + "and not(@href='/prognozy/football/') "
                    + "and not(@href='/prognozy/tennis/') "
                    + "and not(@href='/prognozy/hockey/') "
                    + "and not(contains(@href, '/tag/'))]";

    private final String forecastPageInfo =
            "//*[contains(normalize-space(.), 'Прогноз')]"
                    + " | //*[contains(normalize-space(.), 'ставк')]"
                    + " | //*[contains(normalize-space(.), 'коэффициент')]"
                    + " | //*[contains(normalize-space(.), 'матч')]"
                    + " | //*[contains(normalize-space(.), 'команд')]";

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
        return !findElementsByXpath(forecastCards).isEmpty()
                || isTextPresent("Прогноз");
    }

    public boolean hasFootballCategory() {
        return isElementPresent(footballCategory);
    }

    public boolean hasTennisCategory() {
        return isElementPresent(tennisCategory);
    }

    public boolean hasHockeyCategory() {
        return isElementPresent(hockeyCategory);
    }

    public void openFootballForecasts() {
        openSportCategory(
                footballCategory,
                "Категория футбольных прогнозов не найдена"
        );
    }

    public void openTennisForecasts() {
        openSportCategory(
                tennisCategory,
                "Категория теннисных прогнозов не найдена"
        );
    }

    public void openHockeyForecasts() {
        openSportCategory(
                hockeyCategory,
                "Категория хоккейных прогнозов не найдена"
        );
    }

    public boolean hasSportForecasts(String sportName) {
        return isTextPresent(sportName)
                && hasForecastCards();
    }

    public boolean hasClickableForecasts() {
        return !findElementsByXpath(forecastCards).isEmpty();
    }

    public int getVisibleForecastsCount() {
        return findElementsByXpath(forecastCards).size();
    }

    public String getFirstForecastText() {
        List<WebElement> forecasts = findElementsByXpath(forecastCards);

        if (forecasts.isEmpty()) {
            throw new IllegalStateException("Не найдены карточки прогнозов");
        }

        return forecasts.get(0).getText().trim();
    }

    public void openFirstForecast() {
        List<WebElement> forecasts = findElementsByXpath(forecastCards);

        if (forecasts.isEmpty()) {
            throw new IllegalStateException("Не найдены карточки прогнозов");
        }

        WebElement forecast = forecasts.get(0);

        System.out.println("Открываем прогноз:");
        System.out.println(forecast.getText());
        System.out.println("href = " + forecast.getAttribute("href"));

        scrollTo(forecast);
        jsClick(forecast);
    }

    public boolean hasForecastPageContent() {
        return hasForecastPageHeader()
                && hasForecastMainInfo();
    }

    public boolean hasForecastPageHeader() {
        return isElementPresent("//h1");
    }

    public boolean hasForecastMainInfo() {
        return isElementPresent(forecastPageInfo);
    }

    public String getForecastPageTitle() {
        return findByXpath("//h1").getText().trim();
    }

    private void openSportCategory(String categoryXpath, String errorMessage) {
        if (!isElementPresent(categoryXpath)) {
            throw new IllegalStateException(errorMessage);
        }

        WebElement category = findByXpath(categoryXpath);

        scrollTo(category);
        jsClick(category);
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}