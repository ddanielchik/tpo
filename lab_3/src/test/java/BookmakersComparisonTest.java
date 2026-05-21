import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.BukmekeryPage;
import pages.HomePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookmakersComparisonTest {

    private Utils utils;
    private WebDriver driver;
    private HomePage homePage;
    private BukmekeryPage bukmekeryPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        homePage = new HomePage(driver);
        bukmekeryPage = new BukmekeryPage(driver);
    }

    @Test
    public void userCanCompareBookmakersByRating() {
        homePage.open();
        homePage.openBookmakers();
        assertTrue(bukmekeryPage.hasBookmakersPageContent(), "После перехода не отображается раздел букмекеров");

        List<BukmekeryPage.BookmakerInfo> bookmakers = bukmekeryPage.getBookmakersInfo();
        assertTrue(bookmakers.size() >= 2, "На странице недостаточно букмекеров для сравнения");
        assertTrue(bukmekeryPage.bookmakerCardsContainRatings(), "В найденных карточках букмекеров не отображается рейтинг");

        BukmekeryPage.BookmakerInfo bestBookmaker = bukmekeryPage.getBestRatedBookmaker();
        BukmekeryPage.BookmakerInfo worstBookmaker = bukmekeryPage.getWorstRatedBookmaker();
        assertTrue(bestBookmaker.getRating() >= worstBookmaker.getRating(), "Сравнение рейтингов букмекеров работает некорректно");

        System.out.println("Найденные букмекеры для сравнения:");
        for (BukmekeryPage.BookmakerInfo bookmaker : bookmakers)
            System.out.println(bookmaker.getName() + " — " + bookmaker.getRating());
        System.out.println("Лучший букмекер по рейтингу: " + bestBookmaker.getName() + " — " + bestBookmaker.getRating());
        System.out.println("Минимальный рейтинг среди найденных: " + worstBookmaker.getName() + " — " + worstBookmaker.getRating());
    }

    @Test
    public void userCanOpenFonbetCardAndViewReview() {
        homePage.open();
        homePage.openBookmakers();
        assertTrue(bukmekeryPage.hasBookmakersPageContent(), "После перехода не отображается раздел букмекеров");
        assertTrue(bukmekeryPage.hasFonbetCard(), "Карточка Fonbet не найдена в списке букмекеров");

        bukmekeryPage.openFonbetCard();
        assertTrue(bukmekeryPage.hasBookmakerRatingInfo(), "На странице Fonbet не отображается рейтинг");

        bukmekeryPage.openReviewsSection();
        assertTrue(bukmekeryPage.hasReviewsSection(), "На странице Fonbet не отображается раздел отзывов");
        assertTrue(bukmekeryPage.hasOpenAllReviewsButton(), "Кнопка «Смотреть все отзывы» не отображается");
        assertTrue(bukmekeryPage.hasReviewText(), "В блоке отзывов не найден текст отзыва");

        System.out.println("Пример отзыва о Fonbet:");
        System.out.println(bukmekeryPage.getFirstReviewText());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}