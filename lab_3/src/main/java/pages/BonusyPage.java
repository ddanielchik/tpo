package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BonusyPage extends Page {

    private final String bonusTitle =
            "//*[contains(normalize-space(.), 'Бонусы букмекерских контор')]";

    private final String bonusCards =
            "//*[contains(normalize-space(.), 'Забрать фрибет')]"
                    + " | //*[contains(normalize-space(.), 'PARI')]"
                    + " | //*[contains(normalize-space(.), 'Фонбет')]"
                    + " | //*[contains(normalize-space(.), 'BetBoom')]"
                    + " | //*[contains(normalize-space(.), 'WINLINE')]";

    private final String frebetInfo =
            "//*[contains(normalize-space(.), 'Фрибет')]";

    public BonusyPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/bonusy/");
    }

    public boolean hasBonusesPageContent() {
        return hasBonusesPageHeader()
                && hasBonusOffers();
    }

    public boolean hasBonusesPageHeader() {
        return isElementPresent(bonusTitle);
    }

    public boolean hasBonusOffers() {
        return isElementPresent(bonusCards)
                || isElementPresent(frebetInfo)
                || isTextPresent("Pari")
                || isTextPresent("Fonbet")
                || isTextPresent("BetBoom");
    }

    public void openFirstBonusOffer() {
        List<WebElement> offers = findElementsByXpath(
                "//*[contains(normalize-space(.), 'Получить фрибет')]"
                        + " | //a[contains(@href, '/bukmekery/bonus')]"
                        + " | //a[contains(normalize-space(.), 'Получить')]"
        );

        if (offers.isEmpty()) {
            throw new IllegalStateException("Не найдены бонусные предложения");
        }

        scrollTo(offers.get(0));
        offers.get(0).click();
    }

    public boolean hasBonusConditions() {
        return isElementPresent(frebetInfo);
    }
}