package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EnciklopediyaPage extends Page {

    private final String knowledgeBaseTitle =
                    "//*[contains(normalize-space(.), 'База знаний')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]";

    private final String articleLinks =
            "//a[contains(@href, '/enciklopediya/') and not(@href='/enciklopediya/')]";

    private final String knowledgeBaseInfo =
            "//*[contains(normalize-space(.), 'ставк')]"
                    + " | //*[contains(normalize-space(.), 'беттинг')]"
                    + " | //*[contains(normalize-space(.), 'букмекер')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]";

    private final String articleContentInfo =
            "//*[contains(normalize-space(.), 'ставк')]"
                    + " | //*[contains(normalize-space(.), 'беттинг')]"
                    + " | //*[contains(normalize-space(.), 'букмекер')]"
                    + " | //*[contains(normalize-space(.), 'бонус')]";

    private final String relatedMaterialsInfo =
            "//*[contains(normalize-space(.), 'Читайте также')]"
                    + " | //*[contains(normalize-space(.), 'Похожие материалы')]"
                    + " | //*[contains(normalize-space(.), 'Школа беттинга')]";

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
                || isElementPresent(knowledgeBaseInfo)
                || isTextPresent("Бонус");
    }

    public void openFirstEducationalArticle() {
        List<WebElement> articles = findElementsByXpath(articleLinks);

        if (articles.isEmpty()) {
            throw new IllegalStateException("Не найдены обучающие статьи в базе знаний");
        }

        scrollTo(articles.get(0));
        articles.get(0).click();
    }

    public void openKnownBonusArticle() {
        openPath("/enciklopediya/shkola-bettinga/bonus-v-stavkah-na-sport/");
    }

    public boolean hasArticleMainText() {
        return isElementPresent(articleContentInfo);
    }

    public boolean hasRelatedMaterials() {
        return isElementPresent(articleLinks)
                || isElementPresent(relatedMaterialsInfo);
    }

    public void openFirstRelatedMaterial() {
        List<WebElement> articles = findElementsByXpath(articleLinks);

        if (articles.isEmpty()) {
            throw new IllegalStateException("Не найдены связанные материалы базы знаний");
        }

        scrollTo(articles.get(0));
        articles.get(0).click();
    }
}