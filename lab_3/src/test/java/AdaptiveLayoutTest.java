import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdaptiveLayoutTest extends BaseTest {

    @Test
    public void pageShouldWorkWithMobileScreenSize() {
        driver.manage().window().setSize(new Dimension(390, 844));

        HomePage page = new HomePage(driver);

        page.open();

        assertTrue(page.isOpened());
    }
}