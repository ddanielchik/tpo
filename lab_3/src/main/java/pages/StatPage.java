package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StatPage extends Page {

    private final String matchCenterTitle =
            "//*[contains(normalize-space(.), 'Матчи на сегодня')]"
                    + " | //*[contains(normalize-space(.), 'Матчи по футболу')]";

    private final String matchCards =
            "//a[contains(normalize-space(.), 'Подробнее о матче')]";

    private final String footballLink =
            "//a[contains(@href, '/stat/football/') and contains(normalize-space(.), 'Футбол')]";

    private final String tennisLink =
            "//a[contains(@href, '/stat/tennis/') and contains(normalize-space(.), 'Теннис')]";

    private final String hockeyLink =
            "//a[contains(@href, '/stat/hockey/') and contains(normalize-space(.), 'Хоккей')]";

    private final String yesterdayLink =
            "//a[contains(@href, '/stat/football/yesterday/') and contains(normalize-space(.), 'Футбол вчера')]";

    private final String tomorrowLink =
            "//a[contains(@href, '/stat/football/tomorrow/') and contains(normalize-space(.), 'Футбол завтра')]";

    private final String liveMatchInfo =
            "//*[contains(normalize-space(.), 'Матч идёт')]"
                    + " | //*[contains(normalize-space(.), 'П1')]"
                    + " | //*[contains(normalize-space(.), 'П2')]";

    private final String matchDetailsInfo =
            "//*[contains(normalize-space(.), 'Матч')]"
                    + " | //*[contains(normalize-space(.), 'Счёт')]"
                    + " | //*[contains(normalize-space(.), 'Статистика')]"
                    + " | //*[contains(normalize-space(.), 'П1')]"
                    + " | //*[contains(normalize-space(.), 'П2')]";

    private final String rfplTournamentTable =
            "//a[@href='/stat/football/tournament/89/' and contains(normalize-space(.), 'РФПЛ')]";

    private final String khlTournamentTable =
            "//a[@href='/stat/hockey/tournament/636/' and contains(normalize-space(.), 'КХЛ')]";

    private final String tournamentTableInfo =
            "//table"
                    + " | //*[contains(normalize-space(.), 'Турнир')]"
                    + " | //*[contains(normalize-space(.), 'Таблица')]"
                    + " | //*[contains(normalize-space(.), 'Команда')]"
                    + " | //*[contains(normalize-space(.), 'Очки')]";

    public StatPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openFootball();
    }

    public void openFootball() {
        openPath("/stat/football/");
    }

    public void openTennis() {
        openPath("/stat/tennis/");
    }

    public void openHockey() {
        openPath("/stat/hockey/");
    }

    public void openFootballYesterday() {
        openPath("/stat/football/yesterday/");
    }

    public void openFootballTomorrow() {
        openPath("/stat/football/tomorrow/");
    }

    public boolean hasMatchCenterContent() {
        return hasMatchCenterHeader()
                && (hasMatches() || hasSportNavigation() || hasDayNavigation());
    }

    public boolean hasMatchCenterHeader() {
        return isElementPresent(matchCenterTitle)
                || isTextPresent("Сегодня")
                || isTextPresent("Завтра")
                || isTextPresent("Матчи");
    }

    public boolean hasSportNavigation() {
        return isElementPresent(footballLink)
                && isElementPresent(tennisLink)
                && isElementPresent(hockeyLink);
    }

    public boolean hasDayNavigation() {
        return isElementPresent(yesterdayLink)
                && isElementPresent(footballLink)
                && isElementPresent(tomorrowLink);
    }

    public boolean hasMatches() {
        return isElementPresent(matchCards)
                || isElementPresent(liveMatchInfo);
    }

    public void openFirstMatch() {
        List<WebElement> matches = findElementsByXpath(matchCards);

        if (matches.isEmpty()) {
            throw new IllegalStateException("Не найдены карточки матчей");
        }

        scrollTo(matches.get(0));
        matches.get(0).click();
    }

    public boolean hasMatchPageContent() {
        return hasMatchPageHeader()
                && hasMatchDetails();
    }

    public boolean hasMatchPageHeader() {
        return isElementPresent("//h1");
    }

    public boolean hasMatchDetails() {
        return isElementPresent(matchDetailsInfo);
    }

    public boolean hasTournamentLinks() {
        return isElementPresent(rfplTournamentTable)
                && isElementPresent(khlTournamentTable);
    }

    public void openRfplTournamentTable() {
        openPath("/stat/football/tournament/89/");
    }

    public void openKhlTournamentTable() {
        openPath("/stat/hockey/tournament/636/");
    }

    public boolean hasTournamentTableContent() {
        return isElementPresent(tournamentTableInfo);
    }
}