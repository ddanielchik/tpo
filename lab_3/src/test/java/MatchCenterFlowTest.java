import org.junit.jupiter.api.Test;
import pages.StatPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchCenterFlowTest extends BaseTest {

    @Test
    public void userCanOpenTennisMatchCenter() {
        StatPage page = new StatPage(driver);

        page.openHockeyDirectly();

        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());
        assertTrue(page.hasDaySwitchButtons());
    }

    @Test
    public void userCanSelectDateInTennisCalendar() {
        StatPage page = new StatPage(driver);

        page.openHockeyDirectly();

        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());

        page.openCalendar();

        assertTrue(page.isCalendarOpened());
        assertTrue(page.hasDateInCalendar("2026-05-13"));

        page.selectDateInCalendar("2026-05-13");

        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());
        assertTrue(driver.getCurrentUrl().contains("date=2026-05-13"));
    }

    @Test
    public void userCanSwitchTennisDays() {
        StatPage page = new StatPage(driver);

        page.openHockeyDirectly();

        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());
        assertTrue(page.hasDaySwitchButtons());

        page.clickYesterday();
        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());

        page.clickToday();
        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());

        page.clickTomorrow();
        assertTrue(page.isOpened());
        assertTrue(page.isHockeyPage());
    }

    @Test
    public void userCanSwitchSportSectionsInMatchCenter() {
        StatPage page = new StatPage(driver);

        page.openFootballDirectly();

        assertTrue(page.isOpened());
        assertTrue(page.isFootballPage());

        page.openTennis();
        assertTrue(page.isTennisPage());

        page.openHockey();
        assertTrue(page.isHockeyPage());

        page.openMma();
        assertTrue(page.isMmaPage());
    }
}