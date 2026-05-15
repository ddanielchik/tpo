import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeNavigationTest extends BaseTest {

    @Test
    public void userCanOpenHomePage() {
        HomePage page = new HomePage(driver);

        page.open();

        assertTrue(page.isOpened());
        assertTrue(page.hasMainNavigation());
        assertTrue(page.hasMainContent());
    }

    @Test
    public void userCanNavigateFromHomePageToBonuses() {
        HomePage page = new HomePage(driver);

        page.open();
        page.openBonusy();

        assertTrue(driver.getCurrentUrl().contains("/bonusy/"));
    }

    @Test
    public void userCanNavigateBetweenMainSections() {
        HomePage homePage = new HomePage(driver);
        BonusyPage bonusyPage = new BonusyPage(driver);
        BukmekeryPage bukmekeryPage = new BukmekeryPage(driver);
        PrognozyPage prognozyPage = new PrognozyPage(driver);
        MagPage magPage = new MagPage(driver);
        EnciklopediyaPage enciklopediyaPage = new EnciklopediyaPage(driver);
        StatPage statPage = new StatPage(driver);

        homePage.open();

        assertTrue(homePage.isOpened());
        assertTrue(homePage.hasMainNavigation());

        homePage.openBonusy();
        assertTrue(bonusyPage.isOpened());

        bonusyPage.openBukmekeryPage();
        assertTrue(bukmekeryPage.isOpened());

        bukmekeryPage.openPrognozyPage();
        assertTrue(prognozyPage.isOpened());

        prognozyPage.openMagPage();
        assertTrue(magPage.isOpened());

        magPage.openEnciklopediyaPage();
        assertTrue(enciklopediyaPage.isOpened());

        enciklopediyaPage.openStatFootballPage();
        assertTrue(statPage.isOpened());
    }
}