import org.junit.jupiter.api.Test;
import pages.BukmekeryPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookmakersFlowTest extends BaseTest {

    @Test
    public void userCanOpenBookmakersPageAndSeeCards() {
        BukmekeryPage page = new BukmekeryPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasHeader());
        assertTrue(page.hasBookmakerCards());
        assertTrue(page.getBookmakerCardsCount() > 0);
    }

    @Test
    public void userCanOpenBookmakerCard() {
        BukmekeryPage page = new BukmekeryPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasBookmakerCards());

        page.openFirstBookmakerCard();

        assertTrue(page.hasBookmakerTitle());
    }

    @Test
    public void bookmakerPageShouldContainExternalLinks() {
        BukmekeryPage page = new BukmekeryPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.getExternalLinksCount() >= 0);
    }
}