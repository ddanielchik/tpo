import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.StatPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchCenterTest {

    private Utils utils;
    private WebDriver driver;

    private StatPage statPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        statPage = new StatPage(driver);
    }

    @Test
    public void userCanFindMatchesBySportAndDate() {
        statPage.openFootball();
        assertTrue(statPage.hasMatchCenterContent(), "После открытия матч-центра не отображается раздел матчей");
        assertTrue(statPage.hasFootballPageContent(), "Не отображается страница матчей по футболу");

        statPage.openFootballTomorrow();
        assertTrue(statPage.hasTomorrowFootballPageContent(),
                "После выбора даты «завтра» не открылась страница футбольных матчей на завтра");
        assertTrue(statPage.hasMatches(), "На странице выбранного спорта и даты не отображается список матчей");

        System.out.println("Открыт матч-центр по выбранному виду спорта и дате");
        System.out.println("Количество кликабельных матчей: " + statPage.getVisibleMatchesCount());
    }

    @Test
    public void userCanOpenSpecificMatchPage() {
        statPage.openFootball();
        assertTrue(statPage.hasMatchCenterContent(), "После открытия матч-центра не отображается раздел матчей");
        assertTrue(statPage.hasClickableMatches(), "В матч-центре не найдены кликабельные карточки матчей");

        System.out.println("Первый найденный матч:");
        System.out.println(statPage.getFirstMatchText());

        statPage.openFirstMatch();
        assertTrue(statPage.hasMatchPageContent(),
                "После открытия матча не отображается страница конкретного спортивного события");

        System.out.println("Открыта страница матча:");
        System.out.println(statPage.getMatchPageTitle());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}