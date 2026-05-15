package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MagPage extends Page {

    private final String articleLinksXpath =
            "//a[contains(@href, '/mag/statji/')]";

    public MagPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        String url = baseUrl + "/mag/";
        driver.get(url);
        wait.until(driver -> isOpened());
    }

    @Override
    public boolean isOpened() {
        return getCurrentUrl().contains("/mag/")
                || pageContainsText("журнал");
    }

    public boolean hasNewsBlock() {
        String newsBlockXpath = "//*[contains(normalize-space(), 'Новость дня')]";
        return isElementPresent(newsBlockXpath);
    }

    public boolean hasArticleCards() {
        return getArticleCardsCount() > 0;
    }

    public boolean hasArticlesBlock() {
        return hasArticleCards();
    }

    public boolean hasArticleOrNewsLinks() {
        return hasArticleCards() || hasNewsBlock();
    }

    public int getArticleCardsCount() {
        return countByXpath(articleLinksXpath);
    }

    public void openFirstArticle() {
        List<WebElement> articles = findAllByXpath(articleLinksXpath);

        WebElement firstArticle = articles.get(0);

        String href = firstArticle.getAttribute("href");

        driver.get(href);

        wait.until(driver ->
                getCurrentUrl().contains("/mag/statji/")
        );
    }

    public void openFirstArticleOrNews() {
        openFirstArticle();
    }

    public boolean hasOpenedMaterial() {
        String articleTitleXpath = "//h1 | //h2 | //h3 | //h4";
        return isElementPresent(articleTitleXpath);
    }
}