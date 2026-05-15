import org.junit.jupiter.api.Test;
import pages.BonusyPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusesFlowTest extends BaseTest {

    @Test
    public void userCanOpenBonusesPage() {
        BonusyPage page = new BonusyPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasBonusCards());
    }

    @Test
    public void userCanOpenBookmakerFilterInBonuses() {
        BonusyPage page = new BonusyPage(driver);

        page.open();

        assertTrue(page.isOpened());

        page.openBookmakerFilter();

        assertTrue(page.isBookmakerFilterOpened());
    }

    @Test
    public void userCanSelectBookmakerInBonuses() {
        BonusyPage page = new BonusyPage(driver);

        page.open();

        assertTrue(page.isOpened());

        page.selectBookmaker("BetBoom");

        assertTrue(page.isOpened());
        assertTrue(page.isBookmakerFilterApplied());
    }

    @Test
    public void userCanUseNewPlayersFilterInBonuses() {
        BonusyPage page = new BonusyPage(driver);

        page.open();

        assertTrue(page.isOpened());

        page.selectNewPlayersFilter();

        assertTrue(page.isOpened());
    }

    @Test
    public void userCanUseActivePlayersFilterInBonuses() {
        BonusyPage page = new BonusyPage(driver);

        page.open();

        assertTrue(page.isOpened());

        page.selectActivePlayersFilter();

        assertTrue(page.isOpened());
    }
}