package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EnciklopediyaPage extends Page {

    private final String knowledgeBaseTitle =
            "//*[contains(normalize-space(.), 'База знаний')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]";

    private final String articleLinks =
            "//a[contains(@href, '/enciklopediya/') and not(@href='/enciklopediya/')]";

    private final String searchInput =
            "//input[contains(@placeholder, 'Поиск') "
                    + "or contains(@placeholder, 'поиск') "
                    + "or contains(@placeholder, 'Найти') "
                    + "or contains(@placeholder, 'найти') "
                    + "or @type='search' "
                    + "or @type='text']";

    private final String searchButton =
            "//button[contains(normalize-space(.), 'Найти')]"
                    + " | //button[contains(normalize-space(.), 'Поиск')]"
                    + " | //*[@data-qa='Button' and contains(normalize-space(.), 'Найти')]"
                    + " | //*[@data-qa='Button' and contains(normalize-space(.), 'Поиск')]";

    private final String knowledgeBaseInfo =
            "//*[contains(normalize-space(.), 'ставк')]"
                    + " | //*[contains(normalize-space(.), 'беттинг')]"
                    + " | //*[contains(normalize-space(.), 'букмекер')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]"
                    + " | //*[contains(normalize-space(.), 'Бонус')]";

    private final String articleContentInfo =
            "//*[contains(normalize-space(.), 'ставк')]"
                    + " | //*[contains(normalize-space(.), 'беттинг')]"
                    + " | //*[contains(normalize-space(.), 'букмекер')]"
                    + " | //*[contains(normalize-space(.), 'бонус')]"
                    + " | //*[contains(normalize-space(.), 'фрибет')]"
                    + " | //*[contains(normalize-space(.), 'коэффициент')]";

    private final String relatedMaterialsInfo =
            "//*[contains(normalize-space(.), 'Читайте также')]"
                    + " | //*[contains(normalize-space(.), 'Похожие материалы')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]"
                    + " | //a[contains(@href, '/enciklopediya/') and not(@href='/enciklopediya/')]";

    public EnciklopediyaPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/enciklopediya/");
    }

    public boolean hasKnowledgeBaseContent() {
        return hasKnowledgeBaseHeader()
                && hasArticlesOrCategories();
    }

    public boolean hasKnowledgeBaseHeader() {
        return isElementPresent(knowledgeBaseTitle)
                || isElementPresent(knowledgeBaseInfo);
    }

    public boolean hasArticlesOrCategories() {
        return isElementPresent(articleLinks)
                || isElementPresent(knowledgeBaseInfo);
    }

    public boolean hasSearchInput() {
        return isElementPresent(searchInput);
    }

    public boolean hasSearchButton() {
        return isElementPresent(searchButton);
    }

    public void searchArticle(String query) {
        if (!hasSearchInput()) {
            throw new IllegalStateException("Поле поиска в базе знаний не найдено");
        }

        WebElement input = findByXpath(searchInput);

        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(query);

        System.out.println("Введён поисковый запрос:");
        System.out.println(query);

        clickSearchButton();
    }

    private void clickSearchButton() {
        if (!hasSearchButton()) {
            throw new IllegalStateException("Кнопка поиска не найдена");
        }

        WebElement button = findByXpath(searchButton);

        System.out.println("Нажимаем кнопку поиска:");
        System.out.println(button.getText());

        scrollTo(button);
        button.click();
    }

    public boolean hasSearchResults() {
        return hasClickableArticles();
    }

    public boolean hasSearchResultsFor(String query) {
        return isTextPresent(query)
                && hasClickableArticles();
    }

    public boolean hasClickableArticles() {
        return !findElementsByXpath(articleLinks).isEmpty();
    }

    public int getVisibleArticlesCount() {
        return findElementsByXpath(articleLinks).size();
    }

    public String getFirstArticleText() {
        List<WebElement> articles = findElementsByXpath(articleLinks);

        if (articles.isEmpty()) {
            throw new IllegalStateException("Не найдены обучающие статьи в базе знаний");
        }

        return articles.get(0).getText().trim();
    }

    public void openFirstEducationalArticle() {
        List<WebElement> articles = findElementsByXpath(articleLinks);

        if (articles.isEmpty()) {
            throw new IllegalStateException("Не найдены обучающие статьи в базе знаний");
        }

        WebElement article = articles.get(0);

        System.out.println("Открываем обучающую статью:");
        System.out.println(article.getText());
        System.out.println("href = " + article.getAttribute("href"));

        scrollTo(article);
        article.click();
    }

    public void openKnownBonusArticle() {
        openPath("/enciklopediya/shkola-bettinga/bonus-v-stavkah-na-sport/");
    }

    public boolean hasArticleContent() {
        return hasArticleTitle()
                && hasArticleMainText();
    }

    public boolean hasArticleTitle() {
        return isElementPresent("//h1");
    }

    public boolean hasArticleMainText() {
        return isElementPresent(articleContentInfo);
    }

    public String getArticleTitle() {
        return findByXpath("//h1").getText().trim();
    }

    public boolean hasRelatedMaterials() {
        return isElementPresent(relatedMaterialsInfo);
    }

    public void openFirstRelatedMaterial() {
        List<WebElement> articles = findElementsByXpath(articleLinks);

        if (articles.isEmpty()) {
            throw new IllegalStateException("Не найдены связанные материалы базы знаний");
        }

        WebElement article = articles.get(0);

        System.out.println("Открываем связанный материал базы знаний:");
        System.out.println(article.getText());
        System.out.println("href = " + article.getAttribute("href"));

        scrollTo(article);
        article.click();
    }

    public boolean userStillInsideKnowledgeBase() {
        return driver.getCurrentUrl().contains("/enciklopediya/");
    }
}