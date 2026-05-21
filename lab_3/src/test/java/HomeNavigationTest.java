import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeNavigationTest {

    private Utils utils;
    private WebDriver driver;

    private HomePage homePage;
    private BukmekeryPage bukmekeryPage;
    private BonusyPage bonusyPage;
    private StatPage statPage;
    private PrognozyPage prognozyPage;
    private MagPage magPage;
    private EnciklopediyaPage enciklopediyaPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        homePage = new HomePage(driver);
        bukmekeryPage = new BukmekeryPage(driver);
        bonusyPage = new BonusyPage(driver);
        statPage = new StatPage(driver);
        prognozyPage = new PrognozyPage(driver);
        magPage = new MagPage(driver);
        enciklopediyaPage = new EnciklopediyaPage(driver);
    }

    @Test
    public void userCanNavigateBetweenMainSections() {
        homePage.open();

        assertTrue(homePage.hasHeaderNavigation(), "На главной странице не отображается навигационное меню");

        homePage.openBookmakers();
        assertTrue(bukmekeryPage.hasBookmakersPageContent(), "После перехода не отображается раздел букмекеров");

        homePage.open();
        homePage.openBonuses();
        assertTrue(bonusyPage.hasBonusesPageContent(), "После перехода не отображается раздел бонусов");

        homePage.open();
        homePage.openMatchCenter();
        assertTrue(statPage.hasMatchCenterContent(), "После перехода не отображается матч-центр");

        homePage.open();
        homePage.openForecasts();
        assertTrue(prognozyPage.hasForecastsPageContent(), "После перехода не отображается раздел прогнозов");

        homePage.open();
        homePage.openMagazine();
        assertTrue(magPage.hasMagazinePageContent(), "После перехода не отображается журнал");

        homePage.open();
        homePage.openKnowledgeBase();
        assertTrue(enciklopediyaPage.hasKnowledgeBaseContent(), "После перехода не отображается база знаний");
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}