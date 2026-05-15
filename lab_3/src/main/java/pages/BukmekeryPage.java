package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BukmekeryPage extends Page {

    private final String bookmakerCardsXpath =
            "//a[contains(@href, 'bukmek') or contains(@href, 'book') or contains(@href, 'bk')]";

    public BukmekeryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/bukmekery/";
        driver.get(url);
        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/bukmekery/")
                || pageContainsText("букмекер");
    }

    public boolean hasHeader() {
        String pageHeaderXpath = "//*[contains(normalize-space(), 'Букмекер') or contains(normalize-space(), 'Рейтинг букмекеров')]";
        return isElementPresent(pageHeaderXpath);
    }

    public boolean hasBookmakerCards() {
        return getBookmakerCardsCount() > 0;
    }

    public int getBookmakerCardsCount() {
        return countByXpath(bookmakerCardsXpath);
    }

    public int getBookmakerLinksCount() {
        return getBookmakerCardsCount();
    }

    public void openFirstBookmakerCard() {
        List<WebElement> cards = findAllByXpath(bookmakerCardsXpath);

        if (cards.isEmpty()) {
            throw new IllegalStateException("На странице букмекеров не найдены карточки букмекеров");
        }

        cards.get(0).click();
        wait.until(driver -> !getCurrentUrl().contains("/bukmekery/") || hasBookmakerTitle());
    }

    public boolean hasBookmakerTitle() {
        String bookmakerTitleXpath = "//h1 | //*[contains(normalize-space(), 'Обзор') or contains(normalize-space(), 'Букмекер')]";
        return isElementPresent(bookmakerTitleXpath);
    }

    public int getExternalLinksCount() {
        String externalBookmakerButtonXpath = "//a[starts-with(@href, 'http') and not(contains(@href, 'sravni.bet'))]";
        return countByXpath(externalBookmakerButtonXpath);
    }
}