package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StatPage extends Page {

    private final String matchCenterTitle =
            "//h1[contains(normalize-space(.), 'Матчи по футболу')]"
                    + " | //h1[contains(normalize-space(.), 'Матчи')]";

    private final String footballLink =
            "//a[contains(@href, '/stat/football/') and contains(normalize-space(.), 'футбол')]";

    private final String tennisLink =
            "//a[contains(@href, '/stat/tennis/') and contains(normalize-space(.), 'теннис')]";

    private final String hockeyLink =
            "//a[contains(@href, '/stat/hockey/') and contains(normalize-space(.), 'хоккей')]";

    private final String yesterdayLink =
            "//a[contains(@href, '/stat/football/yesterday/')]";

    private final String tomorrowLink =
            "//a[contains(@href, '/stat/football/tomorrow/')]";

    private final String matchCards =
            "//a[contains(@href, '/stat/futbol/') "
                    + "and not(contains(@href, '/tournament/'))]";

    private final String matchListInfo =
            "//*[contains(normalize-space(.), 'Не начался')]"
                    + " | //*[contains(normalize-space(.), 'Окончен')]"
                    + " | //*[contains(normalize-space(.), 'Идет сейчас')]"
                    + " | //*[contains(normalize-space(.), 'Показать еще')]";

    private final String matchPageInfo =
            "//*[contains(normalize-space(.), 'П1')]"
                    + " | //*[contains(normalize-space(.), 'П2')]"
                    + " | //*[contains(normalize-space(.), 'ТБ')]"
                    + " | //*[contains(normalize-space(.), 'ТМ')]"
                    + " | //*[contains(normalize-space(.), 'Счёт')]"
                    + " | //*[contains(normalize-space(.), 'Статистика')]"
                    + " | //*[contains(normalize-space(.), 'Матч')]"
                    + " | //*[contains(normalize-space(.), 'Не начался')]"
                    + " | //*[contains(normalize-space(.), 'Окончен')]"
                    + " | //*[contains(normalize-space(.), 'Идет сейчас')]";

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
        return isElementPresent(matchCenterTitle);
    }

    public boolean hasSportNavigation() {
        return isElementPresent(footballLink)
                || isElementPresent(tennisLink)
                || isElementPresent(hockeyLink);
    }

    public boolean hasDayNavigation() {
        return isElementPresent(yesterdayLink)
                || isElementPresent(tomorrowLink)
                || isTextPresent("Сегодня")
                || isTextPresent("Завтра")
                || isTextPresent("Вчера");
    }

    public boolean hasMatches() {
        return isElementPresent(matchCards)
                || isElementPresent(matchListInfo);
    }

    public boolean hasClickableMatches() {
        return !findElementsByXpath(matchCards).isEmpty();
    }

    public boolean hasFootballPageContent() {
        return hasMatchCenterHeader()
                && isTextPresent("футбол");
    }

    public boolean hasTomorrowFootballPageContent() {
        return hasMatchCenterHeader()
                && driver.getCurrentUrl().contains("/stat/football/tomorrow/");
    }

    public int getVisibleMatchesCount() {
        return findElementsByXpath(matchCards).size();
    }

    public String getFirstMatchText() {
        List<WebElement> matches = findElementsByXpath(matchCards);

        if (matches.isEmpty()) {
            throw new IllegalStateException("Не найдены кликабельные карточки матчей");
        }

        return matches.get(0).getText().trim();
    }

    public void openFirstMatch() {
        List<WebElement> matches = findElementsByXpath(matchCards);

        if (matches.isEmpty()) {
            throw new IllegalStateException("Не найдены кликабельные карточки матчей");
        }

        WebElement match = matches.get(0);

        System.out.println("Открываем матч:");
        System.out.println(match.getText());
        System.out.println("href = " + match.getAttribute("href"));

        scrollTo(match);
        jsClick(match);
    }

    public boolean hasMatchPageContent() {
        return hasMatchPageHeader()
                && hasMatchDetails();
    }

    public boolean hasMatchPageHeader() {
        return isElementPresent("//h1");
    }

    public boolean hasMatchDetails() {
        return isElementPresent(matchPageInfo);
    }

    public String getMatchPageTitle() {
        return findByXpath("//h1").getText().trim();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}