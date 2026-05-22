import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EnciklopediyaPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncyclopediaSearchTest {

    private Utils utils;
    private WebDriver driver;

    private EnciklopediyaPage enciklopediyaPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        enciklopediyaPage = new EnciklopediyaPage(driver);
    }

    @Test
    public void userCanFindAndOpenEducationalArticleUsingSearch() {
        enciklopediyaPage.open();
        assertTrue(enciklopediyaPage.hasKnowledgeBaseContent(), "После открытия раздела не отображается база знаний");
        assertTrue(enciklopediyaPage.hasSearchInput(), "В базе знаний не найдено поле поиска");
        assertTrue(enciklopediyaPage.hasSearchButton(), "В базе знаний не найдена кнопка поиска");

        enciklopediyaPage.searchArticle("бонус");
        assertTrue(enciklopediyaPage.hasSearchResultsFor("бонус"),
                "После поиска по запросу «бонус» не отображаются подходящие материалы");

        System.out.println("Найдено материалов после поиска: " + enciklopediyaPage.getVisibleArticlesCount());
        System.out.println("Первый найденный материал:");
        System.out.println(enciklopediyaPage.getFirstArticleText());

        enciklopediyaPage.openFirstEducationalArticle();
        assertTrue(enciklopediyaPage.hasArticleContent(),
                "После открытия найденной статьи не отображается её содержимое");
        assertTrue(enciklopediyaPage.userStillInsideKnowledgeBase(),
                "После открытия статьи пользователь оказался вне раздела базы знаний");

        System.out.println("Открыта статья из результатов поиска:");
        System.out.println(enciklopediyaPage.getArticleTitle());
    }

    @Test
    public void userCanOpenRelatedKnowledgeBaseMaterial() {
        enciklopediyaPage.openKnownBonusArticle();
        assertTrue(enciklopediyaPage.hasArticleContent(), "Известная статья базы знаний не отображается");
        assertTrue(enciklopediyaPage.hasRelatedMaterials(), "На странице статьи не найдены связанные материалы базы знаний");

        enciklopediyaPage.openFirstRelatedMaterial();
        assertTrue(enciklopediyaPage.hasArticleContent(), "После перехода к связанному материалу не отображается статья");
        assertTrue(enciklopediyaPage.userStillInsideKnowledgeBase(), "После перехода пользователь оказался вне раздела базы знаний");

        System.out.println("Открыт связанный материал базы знаний:");
        System.out.println(enciklopediyaPage.getArticleTitle());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}