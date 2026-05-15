import org.junit.jupiter.api.Test;
import pages.PrognozyPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrognozyFlowTest extends BaseTest {

    @Test
    public void userCanOpenPrognozyPageAndSeeForecastCards() {
        PrognozyPage page = new PrognozyPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasHeader());
        assertTrue(page.hasForecastCards());
        assertTrue(page.getForecastCardsCount() > 0);
    }

    @Test
    public void userCanFilterPrognozyBySport() {
        PrognozyPage page = new PrognozyPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasSportFilter());

        page.selectSportFilter("Футбол");

        assertTrue(page.isOpened());
        assertTrue(page.hasForecastCards());
    }

    @Test
    public void userCanOpenFirstPrognozyMaterial() {
        PrognozyPage page = new PrognozyPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasForecastCards());

        page.openFirstForecast();

        assertTrue(page.isOpened());
        assertTrue(page.hasForecastMaterialTitle());
    }
}