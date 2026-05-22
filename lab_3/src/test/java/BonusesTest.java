import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.BonusyPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusesTest {

    private Utils utils;
    private WebDriver driver;

    private BonusyPage bonusyPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        bonusyPage = new BonusyPage(driver);
    }

    @Test
    public void userCanViewAndChooseBestBonusOffer() {
        bonusyPage.open();

        assertTrue(
                bonusyPage.hasBonusesPageContent(),
                "После перехода не отображается раздел бонусов"
        );

        assertTrue(
                bonusyPage.hasAtLeastTwoBonusOffers(),
                "На странице недостаточно бонусных предложений для сравнения"
        );

        List<BonusyPage.BonusInfo> bonuses =
                bonusyPage.getVisibleBonusOffers();

        System.out.println("Все доступные бонусные предложения:");
        for (BonusyPage.BonusInfo bonus : bonuses) {
            System.out.println(bonus);
        }

        BonusyPage.BonusInfo bestBonus =
                bonusyPage.getBestBonusOffer();

        System.out.println("Выбран лучший бонус:");
        System.out.println(bestBonus);

        bonusyPage.openBonusOffer(bestBonus);

        assertTrue(
                bonusyPage.hasBonusOfferPageContent(),
                "После выбора бонуса не открылась страница бонусного предложения"
        );

        assertTrue(
                bonusyPage.openedBonusMatches(bestBonus),
                "Открытая страница не соответствует выбранному бонусу"
        );

        System.out.println("Открыта страница бонуса:");
        System.out.println(bonusyPage.getBonusPageTitle());
    }

    @Test
    public void userCanViewBonusOfferConditions() {
        bonusyPage.open();

        assertTrue(
                bonusyPage.hasBonusesPageContent(),
                "После перехода не отображается раздел бонусов"
        );

        BonusyPage.BonusInfo bestBonus =
                bonusyPage.getBestBonusOffer();

        System.out.println("Для проверки условий выбран бонус:");
        System.out.println(bestBonus);

        bonusyPage.openBonusOffer(bestBonus);

        assertTrue(
                bonusyPage.hasBonusOfferPageContent(),
                "Страница бонусного предложения не отображается"
        );

        assertTrue(
                bonusyPage.hasBonusConditions(),
                "На странице бонуса не отображаются условия получения"
        );

        assertTrue(
                bonusyPage.hasGetBonusButton(),
                "На странице бонуса не отображается кнопка перехода к предложению"
        );

        System.out.println("Проверены условия бонусного предложения:");
        System.out.println(bonusyPage.getBonusPageTitle());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}