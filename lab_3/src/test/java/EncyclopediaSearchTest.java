import org.junit.jupiter.api.Test;
import pages.EnciklopediyaPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncyclopediaSearchTest extends BaseTest {

    @Test
    public void userCanOpenEncyclopediaPage() {
        EnciklopediyaPage page = new EnciklopediyaPage(driver);

        page.open();

        assertTrue(page.isOpened());
    }

    @Test
    public void userCanSearchInEncyclopedia() {
        EnciklopediyaPage page = new EnciklopediyaPage(driver);

        page.open();

        assertTrue(page.isOpened());

        page.search("ставки");

        assertEquals("ставки", page.getSearchInputValue());
        assertTrue(page.hasSearchResults("став"));
    }
}