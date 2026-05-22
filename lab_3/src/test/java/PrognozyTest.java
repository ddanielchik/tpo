import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.PrognozyPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrognozyTest {

    private Utils utils;
    private WebDriver driver;

    private PrognozyPage prognozyPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        prognozyPage = new PrognozyPage(driver);
    }

    @Test
    public void userCanFindForecastsBySport() {
        prognozyPage.open();
        assertTrue(prognozyPage.hasForecastsPageContent(), "После открытия раздела не отображается страница прогнозов");
        assertTrue(prognozyPage.hasFootballCategory(), "На странице не найдена категория футбольных прогнозов");

        prognozyPage.openFootballForecasts();
        assertTrue(prognozyPage.hasSportForecasts("Футбол"), "После выбора футбола не отображаются футбольные прогнозы");
        assertTrue(prognozyPage.hasClickableForecasts(), "На странице футбольных прогнозов не найдены карточки прогнозов");

        System.out.println("Открыта категория футбольных прогнозов");
        System.out.println("Количество найденных прогнозов: " + prognozyPage.getVisibleForecastsCount());
    }

    @Test
    public void userCanOpenForecastAndViewContent() {
        prognozyPage.open();
        assertTrue(prognozyPage.hasForecastsPageContent(), "После открытия раздела не отображается страница прогнозов");
        assertTrue(prognozyPage.hasClickableForecasts(), "На странице не найдены карточки прогнозов");

        System.out.println("Первый найденный прогноз:");
        System.out.println(prognozyPage.getFirstForecastText());

        prognozyPage.openFirstForecast();
        assertTrue(prognozyPage.hasForecastPageContent(), "После открытия прогноза не отображается его основное содержимое");

        System.out.println("Открыта страница прогноза:");
        System.out.println(prognozyPage.getForecastPageTitle());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}