import org.junit.jupiter.api.Test;
import pages.MagPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MagazineFlowTest extends BaseTest {

    @Test
    public void userCanOpenMagazinePageAndSeeMaterials() {
        MagPage page = new MagPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasArticlesBlock());
        assertTrue(page.hasNewsBlock());
        assertTrue(page.hasArticleOrNewsLinks());
    }

    @Test
    public void userCanOpenFirstMagazineMaterial() {
        MagPage page = new MagPage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasArticleOrNewsLinks());

        page.openFirstArticleOrNews();

        assertTrue(page.hasOpenedMaterial());
    }
}