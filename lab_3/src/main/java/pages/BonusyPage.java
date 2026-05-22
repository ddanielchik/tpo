package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BonusyPage extends Page {

    public static class BonusInfo {
        private final String title;
        private final String bookmaker;
        private final int amount;
        private final String type;
        private final WebElement card;

        public BonusInfo(String title, String bookmaker, int amount, String type, WebElement card) {
            this.title = title;
            this.bookmaker = bookmaker;
            this.amount = amount;
            this.type = type;
            this.card = card;
        }

        public String getTitle() {
            return title;
        }

        public String getBookmaker() {
            return bookmaker;
        }

        public int getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public WebElement getCard() {
            return card;
        }

        @Override
        public String toString() {
            return bookmaker + " — " + amount + " ₽ — " + type + " — " + title;
        }
    }

    private final String bonusPageTitle =
            "//*[contains(normalize-space(.), 'Бонусы букмекерских контор')]";

    private final String bonusCards =
            "//a[contains(@href, '/bonusy/') and contains(normalize-space(.), '₽')]";

    private final String bonusConditions =
            "//*[contains(normalize-space(.), 'Ключевые условия')]"
                    + " | //*[contains(normalize-space(.), 'Как получить')]"
                    + " | //*[contains(normalize-space(.), 'Правила')]"
                    + " | //*[contains(normalize-space(.), 'Отыгрыш')]"
                    + " | //*[contains(normalize-space(.), 'Мин. депозит')]";

    private final String getBonusButton =
            "//*[contains(normalize-space(.), 'Забрать фрибет')]"
                    + " | //*[contains(normalize-space(.), 'Забрать бонус')]"
                    + " | //*[contains(normalize-space(.), 'Получить фрибет')]";

    public BonusyPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/bonusy/");
    }

    public boolean hasBonusesPageContent() {
        return isElementPresent(bonusPageTitle)
                && hasBonusOffers();
    }

    public boolean hasBonusOffers() {
        return !findElementsByXpath(bonusCards).isEmpty();
    }

    public List<BonusInfo> getVisibleBonusOffers() {
        List<BonusInfo> bonuses = new ArrayList<>();

        for (WebElement card : findElementsByXpath(bonusCards)) {
            String text = card.getText().trim();

            if (text.isEmpty()) {
                continue;
            }

            String title = extractTitle(text);
            String bookmaker = extractBookmakerFromTitle(title);
            int amount = extractAmount(text);
            String type = extractType(text);

            bonuses.add(new BonusInfo(title, bookmaker, amount, type, card));
        }

        return bonuses;
    }

    public boolean hasAtLeastTwoBonusOffers() {
        return getVisibleBonusOffers().size() >= 2;
    }

    public BonusInfo getBestBonusOffer() {
        List<BonusInfo> bonuses = getVisibleBonusOffers();

        if (bonuses.isEmpty()) {
            throw new IllegalStateException("Бонусные предложения не найдены");
        }

        BonusInfo bestBonus = bonuses.get(0);

        for (BonusInfo bonus : bonuses) {
            if (bonus.getAmount() > bestBonus.getAmount()) {
                bestBonus = bonus;
            }
        }

        return bestBonus;
    }

    public void openBonusOffer(BonusInfo bonus) {
        System.out.println("Открываем бонус:");
        System.out.println(bonus);
        System.out.println("href = " + bonus.getCard().getAttribute("href"));

        scrollTo(bonus.getCard());
        jsClick(bonus.getCard());
    }

    public void openFirstBonusOffer() {
        List<BonusInfo> bonuses = getVisibleBonusOffers();

        if (bonuses.isEmpty()) {
            throw new IllegalStateException("Бонусные предложения не найдены");
        }

        openBonusOffer(bonuses.get(0));
    }

    public boolean hasBonusOfferPageContent() {
        return isElementPresent("//h1")
                && hasBonusConditions();
    }

    public boolean hasBonusConditions() {
        return isElementPresent(bonusConditions);
    }

    public boolean hasGetBonusButton() {
        return isElementPresent(getBonusButton);
    }

    public boolean openedBonusMatches(BonusInfo bonus) {
        return getBonusPageTitle().contains(bonus.getBookmaker());
    }

    public String getBonusPageTitle() {
        return findByXpath("//h1").getText().trim();
    }

    private String extractTitle(String text) {
        for (String line : text.split("\\R")) {
            String value = line.trim();

            if (!value.isEmpty()
                    && !value.contains("₽")
                    && !value.equalsIgnoreCase("Бонус")
                    && !value.equalsIgnoreCase("Фрибет")
                    && !value.toLowerCase().contains("истекает")) {
                return value;
            }
        }

        throw new IllegalStateException("Не удалось определить название бонуса");
    }

    private String extractBookmakerFromTitle(String title) {
        if (title.contains(":")) {
            return title.substring(0, title.indexOf(":")).trim();
        }

        return title.split("\\s+")[0].trim();
    }

    private int extractAmount(String text) {
        String normalizedText = text
                .replace(" ", "")
                .replace("\u00A0", "");

        Pattern pattern = Pattern.compile("(\\d+)₽");
        Matcher matcher = pattern.matcher(normalizedText);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new IllegalStateException("Не удалось определить сумму бонуса");
    }

    private String extractType(String text) {
        if (text.contains("Фрибет")) {
            return "Фрибет";
        }

        if (text.contains("Бонус")) {
            return "Бонус";
        }

        return "Неизвестный тип";
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}